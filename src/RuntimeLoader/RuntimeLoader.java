package RuntimeLoader;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RuntimeLoader extends ClassLoader {
    private Map<String, byte[]> classMap;
    private Map<String, Class<?>> objectMap;

    public class RuntimeResponse {
        public Class<?> runtime;
        public Constructor<?> constructor;
        public Object instance;
        public Method method;

        public RuntimeResponse() {
            runtime = null;
            constructor = null;
            instance = null;
            method = null;
        }
    }

    public RuntimeLoader() {
        classMap = new HashMap<String, byte[]>();
        objectMap = new HashMap<String, Class<?>>();
    }

    public void add(String className, String classFileName)
            throws IllegalArgumentException, FileNotFoundException, IOException {
        if (classMap.containsKey(className)) {
            throw new IllegalArgumentException("多重登録になりました。");
        }

        var file = new File(classFileName);
        var length = file.length();
        var fis = new FileInputStream(file);
        var bis = new BufferedInputStream(fis);
        var dis = new DataInputStream(bis);
        var binary = new byte[(int) length];

        for (int i = 0; i < length; i++) {
            binary[i] = dis.readByte();
        }

        // TODO:ふと思いましたが、バイナリを保存するより
        // インスタンスをもう作って保存した方が、早いのでは
        // ないでしょうか…。
        classMap.put(className, binary);

        dis.close();
        bis.close();
        fis.close();
    }

    public RuntimeResponse getInstance(String className, Class<?>[] paramTypesConstructor, Object[] paramConstructor)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var runtimeResponse = new RuntimeResponse();

        runtimeResponse.runtime = this.findClass(className);
        runtimeResponse.constructor = runtimeResponse.runtime.getConstructor(paramTypesConstructor);
        runtimeResponse.instance = runtimeResponse.constructor.newInstance(paramConstructor);

        return runtimeResponse;
    }

    public Object invoke(RuntimeResponse runtimeResponse, String className, String methodName,
            Class<?>[] paramTypesMethod, Object[] argsMethod)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var myMethod = runtimeResponse.runtime.getMethod(methodName, paramTypesMethod);
        var response = myMethod.invoke(runtimeResponse.instance, argsMethod);

        return response;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        var response = this.objectMap.get(className);
        if (response != null) {
            return response;
        }

        var binary = this.classMap.get(className);
        if (binary == null) {
            throw new ClassNotFoundException(className);
        }

        response = defineClass(className, binary, 0, binary.length);
        this.objectMap.put(className, response);

        return response;
    }
}
