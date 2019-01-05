package Annotation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import Exception.ApplicationException;
import RuntimeLoader.RuntimeLoader;

public class LogicDrive {
    private RuntimeLoader runtimeLoader = null;

    public LogicDrive() {
        this.runtimeLoader = new RuntimeLoader();
    }

    public boolean initialize(String targetDirectory) throws ApplicationException {
        FilenameFilter filter = (file, name) -> {
            if (name.contains("LogicBase")) {
                return false;
            } else if (name.endsWith(".class")) {
                return true;
            } else {
                return false;
            }
        };

        try {
            var files = new File(targetDirectory);
            if (files != null && files.isDirectory()) {
                var listFiles = files.listFiles(filter);
                if (listFiles != null) {
                    for (var file : listFiles) {
                        var className = file.getName();
                        var classFileName = file.getAbsolutePath();
                        this.runtimeLoader.add(className, classFileName);
                        // ToDo:いずれはLog4j2などで、ログに出力する。
                        System.out.println("className:" + className + " classFileName:" + classFileName);
                    }
                }
            }

        } catch (IllegalArgumentException | IOException e) {
            throw new ApplicationException(e);
        }
        return true;
    }

    public boolean callFunction(int stream, int function) {
        // TODO:途中
        this.runtimeLoader.findByAnnotation(1, 1);

        return false;
    }
}
