package samplecutter.fileoperations;

import samplecutter.filepreparation.WavFilesDirPathCreator;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class BrowseRunner extends Thread {
    private final boolean browseSupported;

    BrowseRunner() {
        super();
        browseSupported = Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        start();
    }

    public void run() {
        if (browseSupported) {
            try {
                String targetDirString = WavFilesDirPathCreator.get();
                Desktop.getDesktop().browse(new URI("file:" + targetDirString));
            } catch (URISyntaxException | IOException exc) {
                exc.printStackTrace();
            }
        }
    }
}
