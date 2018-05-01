package samplecutter.fxapplication;

import samplecutter.config.ConfigReader;
import samplecutter.filepreparation.FileNameFilterOnExtensionCreator;
import samplecutter.filepreparation.WavFilesDirPathCreator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class WavFilesNameArrCreator {
    private String[] wavFilesNameArr;

    public WavFilesNameArrCreator() {
        FilenameFilter fileNameOnExt = new FileNameFilterOnExtensionCreator(ConfigReader.getFileType());
        wavFilesNameArr = (new File(WavFilesDirPathCreator.get())).list(fileNameOnExt);
        if (wavFilesNameArr != null) {
            Arrays.sort(wavFilesNameArr);
        }
    }

    public String[] get() {
        return wavFilesNameArr;
    }
}
