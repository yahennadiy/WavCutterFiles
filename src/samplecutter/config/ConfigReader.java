package samplecutter.config;

import java.lang.reflect.Method;

public class ConfigReader {
    private static AnnotationConfigInterface configParameters;
    public static void exec() {
        try {
            AnnotationConfigClass configParametersObject = new AnnotationConfigClass();
            Method configParametersMethod = configParametersObject.getClass().getMethod(
                    "configParametersMethod");
            configParameters = configParametersMethod.getAnnotation(AnnotationConfigInterface.class);
        } catch (Exception e) {
            System.out.println("Annotation Error: " + e);
        }
    }

    public static String getFileType() {
        return configParameters.fileType;
    }

    public static String getFileFormat() {
        return configParameters.fileFormat;
    }

    public static int getServiceInfoLength() {
        return configParameters.serviceInfoLength;
    }

    public static int getBufferSize() {
        return configParameters.bufferSize;
    }

    public static String getWavCutterDirName() {
        return configParameters.wavCutterDirName;
    }
}
