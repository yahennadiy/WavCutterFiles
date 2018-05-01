package samplecutter.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ interface AnnotationConfigInterface {
    String fileType = "wav";
    String fileFormat = "WAVE";
    int serviceInfoLength = 44;
    int bufferSize = 600000;
    String wavCutterDirName = "/WavCutterFiles/";
}
