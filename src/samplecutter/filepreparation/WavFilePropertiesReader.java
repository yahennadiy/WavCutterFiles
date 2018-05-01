package samplecutter.filepreparation;

import javafx.application.Platform;
import samplecutter.config.ConfigReader;
import samplecutter.fxapplication.mainscene.FilesNamesListView;
import samplecutter.fxapplication.mainscene.MainSceneCreator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WavFilePropertiesReader {
    private static int serviceInfoLength = ConfigReader.getServiceInfoLength();
    private static byte[] serviceInfo = new byte[serviceInfoLength];

    public WavFilePropertiesReader() {
        String inFilePath = WavFilesDirPathCreator.get().concat(FilesNamesListView.getFileName());
        try (FileChannel inChan =
                     (FileChannel) Files.newByteChannel(Paths.get(inFilePath),
                             StandardOpenOption.READ)) {
            ByteBuffer serviceBuffer = ByteBuffer.allocate(serviceInfoLength);
            inChan.read(serviceBuffer);
            serviceBuffer.rewind();
            for (int i = 0; i < serviceInfoLength; i++) {
                serviceInfo[i] = serviceBuffer.get();
            }
        } catch (IOException exc) {
            System.out.println("Exception in WavFilePropertiesReader : " + exc);
            Platform.runLater(() -> {
                MainSceneCreator.refresh();
                MainSceneCreator.getActionInfoLbl().setText("Sorry, file not exist");
            });
        }
    }

    public boolean isWAVE() {
        return getServiceStr(8, 11).equals(ConfigReader.getFileFormat());
    }

    public int getBitsPerSample() {
        return getServiceNum(34, 35);
    }

    private static String getServiceStr(int firstByte, int lastByte) {
        StringBuilder sb = new StringBuilder();
        for (int i = firstByte; i <= lastByte; i++) {
            sb.append((char) serviceInfo[i]);
        }

        return sb.toString();
    }

    private static int getServiceNum(int firstByte, int lastByte) {
        int result = 0;
        for (int i = lastByte; i >= firstByte; i--) {
            result = result | (0xff & serviceInfo[i]);
            if (i > firstByte) {
                result = result << 8;
            }
        }

        return  result;
    }
}
