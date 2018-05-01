package samplecutter.filepreparation;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilterOnExtensionCreator implements FilenameFilter {
    private String extension;

    public FileNameFilterOnExtensionCreator(String extension) {
        this.extension = "." + extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}
