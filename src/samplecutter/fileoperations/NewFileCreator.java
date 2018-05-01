package samplecutter.fileoperations;

import javafx.application.Platform;
import samplecutter.config.ConfigReader;
import samplecutter.filepreparation.WavFilesDirPathCreator;
import samplecutter.fxapplication.mainscene.FilesNamesListView;
import samplecutter.fxapplication.mainscene.MainSceneCreator;
import samplecutter.fxapplication.mainscene.SampleBitsRemainListView;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NewFileCreator extends Thread {
    public NewFileCreator() {
        super();
        start();
    }

    public void run() {
        String fileDir = WavFilesDirPathCreator.get();
        String fileType = ConfigReader.getFileType();
        String inFileName = FilesNamesListView.getFileName();
        Path inFilePath = Paths.get(fileDir + inFileName);
        String depth = SampleBitsRemainListView.getDepth();
        String outFileName = ((new StringBuilder()).
                append(inFileName.substring(0, inFileName.length() - (fileType.length() + 1))).
                append(depth).append("bits.").append(fileType)).toString();
        Path outFilePath = Paths.get(fileDir.concat(outFileName));
        try (FileChannel inChan = (FileChannel) Files.newByteChannel(inFilePath,
                             StandardOpenOption.READ);
             FileChannel outChan = (FileChannel) Files.newByteChannel(outFilePath,
                             StandardOpenOption.WRITE,
                             StandardOpenOption.CREATE)) {
            ByteBuffer serviceBuffer = ByteBuffer.allocate(ConfigReader.getServiceInfoLength());
            inChan.read(serviceBuffer);
            serviceBuffer.rewind();
            outChan.write(serviceBuffer);
            int bufferLength = ConfigReader.getBufferSize();
            byte data[] = new byte[bufferLength];
            ByteBuffer dataInBuf = ByteBuffer.allocate(bufferLength);
            ByteBuffer dataOutBuf = ByteBuffer.allocate(bufferLength);
            int count = inChan.read(dataInBuf);
            while (count != -1) {
                if (count < bufferLength) {
                    dataOutBuf = ByteBuffer.allocate(count);
                }

                dataInBuf.rewind();
                for (int i = 0; i < count; i++) {
                    data[i] = dataInBuf.get();
                }

                data = DataOutArrCreator.exec(data);
                for (int i = 0; i < count; i++) {
                    dataOutBuf.put(data[i]);
                }

                dataOutBuf.rewind();
                outChan.write(dataOutBuf);
                dataInBuf.rewind();
                dataOutBuf.rewind();
                count = inChan.read(dataInBuf);
            }

            Platform.runLater(() -> {
                MainSceneCreator.refresh();
                MainSceneCreator.getActionInfoLbl().setText("Execution complete");
            });
            new BrowseRunner();
        } catch (IOException exc) {
            System.out.println("Exception in NewFileCreator: " + exc);
            Platform.runLater(() -> {
                MainSceneCreator.refresh();
                MainSceneCreator.getActionInfoLbl().setText("Sorry, Execution error");
            });
        }
    }
}
