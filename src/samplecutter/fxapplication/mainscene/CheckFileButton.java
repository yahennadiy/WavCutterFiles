package samplecutter.fxapplication.mainscene;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import samplecutter.filepreparation.WavFilePropertiesReader;

public class CheckFileButton {
    private static int sampleLength;
    static Button create() {
        Button checkFileBtn = new Button("Check file");
        checkFileBtn.setOnAction((ActionEvent actionEvent) -> {
            WavFilePropertiesReader wavFileProperties = new WavFilePropertiesReader();
            sampleLength = wavFileProperties.getBitsPerSample();
            MainSceneCreator.getSampleBitsRemainLv().setDisable(true);
            MainSceneCreator.getExecuteBtn().setDisable(true);
            if (!wavFileProperties.isWAVE()) {
                MainSceneCreator.getSampleLengthLbl().setText("File is not WAVE format");
            } else {
                if (!(sampleLength == 16 || sampleLength == 24)) {
                    MainSceneCreator.getSampleLengthLbl().setText("Sample is not 16 or 24");
                } else {
                    MainSceneCreator.getSampleLengthLbl().setText(
                            (new StringBuilder()).append("Sample length: ").append(sampleLength).
                                    append(" bits").toString());
                    MainSceneCreator.setSampleBitsRemainListViewRepaint(sampleLength);
                    MainSceneCreator.getSampleBitsRemainLv().setDisable(false);
                }
            }
        });
        return checkFileBtn;
    }

    public static int getSampleLength() {
        return sampleLength;
    }
}
