package samplecutter.filepreparation;

import samplecutter.config.ConfigReader;

import java.io.File;

public class WavFilesDirPathCreator {
    public static String get() {
        StringBuilder stringBuilder = new StringBuilder();
        String wavFilesDirPath = "";
        String wavCutterDirName = ConfigReader.getWavCutterDirName();
        if (isUnix()) {
            stringBuilder = stringBuilder.append("/home/").append(System.getProperty("user.name")).
                    append(wavCutterDirName);
            wavFilesDirPath = stringBuilder.toString();
        }

        if (isMac()) {
            stringBuilder.append("/Users/").append(System.getProperty("user.name")).append(wavCutterDirName);
            wavFilesDirPath = stringBuilder.toString();
        }

        if (isWindows()) {
            wavFilesDirPath = "c:".concat(wavCutterDirName);
        }

        File wavFilesDirPathFile = new File(wavFilesDirPath);
        if (!wavFilesDirPathFile.exists()) {
            wavFilesDirPathFile.mkdir();
        }

        return wavFilesDirPath;
    }

    private static boolean isUnix(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("nix") || os.contains("nux"));
    }

    private static boolean isMac(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("mac"));
    }

    private static boolean isWindows(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));
    }
}
