package samplecutter.fxapplication.mainscene;

import javafx.scene.control.Button;
import samplecutter.fileoperations.NewFileCreator;

class ExecuteButton {
    static Button create() {
        Button executeBtn = new Button("Execute");
        executeBtn.setOnAction(actionEvent -> {
            MainSceneCreator.getCheckFileBtn().setDisable(true);
            MainSceneCreator.getExecuteBtn().setDisable(true);
            MainSceneCreator.getRefreshDirBtn().setDisable(true);
            MainSceneCreator.getFilesNamesLv().setDisable(true);
            MainSceneCreator.getSampleBitsRemainLv().setDisable(true);
            MainSceneCreator.getActionInfoLbl().setText("Wait, please");
            new NewFileCreator();
        });
        return executeBtn;
    }
}
