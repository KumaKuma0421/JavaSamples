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

import Annotation.InjectionInfo;

public class RuntimeLoader extends ClassLoader {
    private Map<String, byte[]> binaryMap;
    private Map<String, Class<?>> classMap;

    public class RuntimeResponse {
        public Class<?> theClass;
        public Constructor<?> theConstructor;
        public Object newInstance;
        public Method method;

        public RuntimeResponse() {
            theClass = null;
            theConstructor = null;
            newInstance = null;
            method = null;
        }
    }

    public RuntimeLoader() {
        binaryMap = new HashMap<String, byte[]>();
        classMap = new HashMap<String, Class<?>>();
    }

    public void add(String className, String classFileName)
            throws IllegalArgumentException, FileNotFoundException, IOException {
        if (binaryMap.containsKey(className)) {
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
        // と、思ったのですが、コンストラクタ呼び出しをルール化
        // しないと行けないです・・・。
        binaryMap.put(className, binary);

        dis.close();
        bis.close();
        fis.close();
    }

    public RuntimeResponse getInstance(String className, Class<?>[] paramTypesConstructor, Object[] paramConstructor)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var runtimeResponse = new RuntimeResponse();

        runtimeResponse.theClass = this.findClass(className);
        runtimeResponse.theConstructor = runtimeResponse.theClass.getConstructor(paramTypesConstructor);
        runtimeResponse.newInstance = runtimeResponse.theConstructor.newInstance(paramConstructor);

        return runtimeResponse;
    }

    public Object invoke(RuntimeResponse runtimeResponse, String methodName, Class<?>[] paramTypesMethod,
            Object[] argsMethod) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var myMethod = runtimeResponse.theClass.getMethod(methodName, paramTypesMethod);
        var response = myMethod.invoke(runtimeResponse.newInstance, argsMethod);

        return response;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        var response = this.classMap.get(className);
        if (response != null) {
            return response;
        }

        var binary = this.binaryMap.get(className);
        if (binary == null) {
            throw new ClassNotFoundException(className);
        }

        response = defineClass(className, binary, 0, binary.length);
        this.classMap.put(className, response);

        return response;
    }

    public Class<?> findByAnnotation(int stream, int function) {
        Class<?> response = null;

        for (var theObject : this.classMap.values()) {
            for (var theMethod : theObject.getMethods()) {
                var targetInjectionInfo = theMethod.getAnnotation(InjectionInfo.class);
                if (targetInjectionInfo != null && targetInjectionInfo.stream() == stream
                        && targetInjectionInfo.function() == function) {
                    return theObject;
                }
            }
        }

        return response;
    }
}
