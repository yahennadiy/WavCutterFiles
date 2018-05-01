package samplecutter.fxapplication.mainscene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import samplecutter.fxapplication.WavFilesNameArrCreator;

public class FilesNamesListView {
    private static String fileName;
    public static ListView<String> create() {
        ObservableList<String> filesNameObsList =
                FXCollections.observableArrayList((new WavFilesNameArrCreator()).get());
        ListView<String> filesNamesLv = new ListView<String>(filesNameObsList);
        MultipleSelectionModel<String> lvSelModelFiles =
                filesNamesLv.getSelectionModel();
        lvSelModelFiles.selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    fileName = newValue;
                    MainSceneCreator.getFileNameLbl().setText("Selected file: ".concat(fileName));
                    MainSceneCreator.getCheckFileBtn().setDisable(false);
                    MainSceneCreator.getExecuteBtn().setDisable(true);
                    MainSceneCreator.getSampleBitsRemainLv().getSelectionModel().clearSelection();
                    MainSceneCreator.getSampleBitsRemainLv().setDisable(true);
                    MainSceneCreator.getSampleLengthLbl().setText("Sample length: ");
                    MainSceneCreator.getActionInfoLbl().setText("");
                }
        );
        return filesNamesLv;
    }

    public static String getFileName() {
        return fileName;
    }
}
