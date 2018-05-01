package samplecutter.fxapplication.mainscene;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import samplecutter.fxapplication.Controller;
import samplecutter.fxapplication.SampleLengthArrCreator;

public class MainSceneCreator {
    private static Scene mainScene;
    private static GridPane mainRoot;
    private static ListView<String> filesNamesLv;
    private static Button checkFileBtn;
    private static ListView<String> sampleBitsRemainLv;
    private static ListView<String> sampleBitsRemain16Lv;
    private static ListView<String> sampleBitsRemain24Lv;
    private static Button executeBtn;
    private static Button refreshDirBtn;
    private static Label fileNameLbl;
    private static Label sampleLengthLbl;
    private static Label actionInfoLbl;

    public static void create() {
        final int GAP = Controller.getGap();
        mainRoot = new GridPane();
        mainRoot.setPadding(Controller.getInsets());
        mainRoot.setHgap(GAP);
        mainRoot.setVgap(GAP);
        mainRoot.setAlignment(Pos.CENTER);
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setHgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.NEVER);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.NEVER);
        mainRoot.getColumnConstraints().addAll(col0, col1, col2, col3);
        RowConstraints row0 = new RowConstraints();
        row0.setVgrow(Priority.NEVER);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.NEVER);
        RowConstraints row3 = new RowConstraints();
        row3.setVgrow(Priority.ALWAYS);
        RowConstraints row4 = new RowConstraints();
        row4.setVgrow(Priority.NEVER);
        RowConstraints row5 = new RowConstraints();
        row5.setVgrow(Priority.NEVER);
        RowConstraints row6 = new RowConstraints();
        row6.setVgrow(Priority.NEVER);
        RowConstraints row7 = new RowConstraints();
        row7.setVgrow(Priority.NEVER);
        mainRoot.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6, row7);
        Label aboutLbl = new Label("The program shifts the data in the audio samples to the selected bits" +
                " number.");
        aboutLbl.setWrapText(true);
        GridPane.setColumnSpan(aboutLbl,4);
        GridPane.setHalignment(aboutLbl, HPos.LEFT);
        Label instructionLbl = new Label("Put your wav files into WavCutterFiles directory of root directory" +
                " and press Refresh Directory.");
        instructionLbl.setWrapText(true);
        GridPane.setColumnSpan(instructionLbl,4);
        GridPane.setHalignment(instructionLbl, HPos.LEFT);
        Label coutionLbl = new Label("***CAUTION!!!***" +
                " After playing quiet samples, return the volume control to the normal position! Incorrect" +
                " actions with volume control can DAMAGE your HEALTH!!! and damage your Hi-Fi system!");
        coutionLbl.setWrapText(true);
        GridPane.setColumnSpan(coutionLbl,4);
        GridPane.setHalignment(coutionLbl, HPos.LEFT);
        filesNamesLv = FilesNamesListView.create();
        checkFileBtn = CheckFileButton.create();
        sampleBitsRemain16Lv = SampleBitsRemainListView.create(SampleLengthArrCreator.get(16));
        sampleBitsRemain24Lv = SampleBitsRemainListView.create(SampleLengthArrCreator.get(24));
        sampleBitsRemainLv = sampleBitsRemain16Lv;
        executeBtn = ExecuteButton.create();
        refreshDirBtn = RefreshDirButton.create();
        fileNameLbl = new Label("Selected file: ");
        GridPane.setColumnSpan(fileNameLbl,4);
        GridPane.setHalignment(fileNameLbl, HPos.LEFT);
        sampleLengthLbl = new Label("Sample length: ");
        GridPane.setColumnSpan(sampleLengthLbl,4);
        GridPane.setHalignment(sampleLengthLbl, HPos.LEFT);
        actionInfoLbl = new Label("");
        GridPane.setColumnSpan(actionInfoLbl,4);
        GridPane.setHalignment(actionInfoLbl, HPos.LEFT);
        final int HSIZE = Controller.getHSize();
        final int VSIZE = Controller.getVSIZE();
        mainScene = new Scene(mainRoot, HSIZE, VSIZE);
        mainRoot.add(aboutLbl, 0, 0);
        mainRoot.add(instructionLbl, 0, 1);
        mainRoot.add(coutionLbl, 0, 2);
        mainRoot.add(filesNamesLv, 0, 3);
        mainRoot.add(checkFileBtn, 1, 3);
        mainRoot.add(sampleBitsRemainLv, 2, 3);
        mainRoot.add(executeBtn, 3, 3);
        mainRoot.add(refreshDirBtn, 0, 4);
        mainRoot.add(fileNameLbl, 0, 5);
        mainRoot.add(sampleLengthLbl, 0, 6);
        mainRoot.add(actionInfoLbl, 0, 7);
    }

    public static void refresh() {
        filesNamesListViewRepaint();
        filesNamesLv.getSelectionModel().clearSelection();
        sampleBitsRemainLv.getSelectionModel().clearSelection();
        checkFileBtn.setDisable(true);
        sampleBitsRemainLv.setDisable(true);
        executeBtn.setDisable(true);
        filesNamesLv.setDisable(false);
        refreshDirBtn.setDisable(false);
        fileNameLbl.setText("Selected file: ");
        sampleLengthLbl.setText("Sample length: ");
        actionInfoLbl.setText("");
    }

    private static void filesNamesListViewRepaint() {
        mainRoot.getChildren().remove(filesNamesLv);
        filesNamesLv = FilesNamesListView.create();
        mainRoot.add(filesNamesLv, 0, 3);
    }

    public static void setSampleBitsRemainListViewRepaint(int sampleLength) {
        mainRoot.getChildren().remove(sampleBitsRemainLv);
        if (sampleLength == 24) {
            sampleBitsRemainLv = sampleBitsRemain24Lv;
        } else {
            sampleBitsRemainLv = sampleBitsRemain16Lv;
        }

        mainRoot.add(sampleBitsRemainLv, 2, 3);
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static ListView<String> getFilesNamesLv() {
        return filesNamesLv;
    }

    public static Button getCheckFileBtn() {
        return checkFileBtn;
    }

    public static ListView<String> getSampleBitsRemainLv() {
        return sampleBitsRemainLv;
    }

    public static Button getExecuteBtn() {
        return executeBtn;
    }

    public static Button getRefreshDirBtn() {
        return refreshDirBtn;
    }

    public static Label getFileNameLbl() {
        return fileNameLbl;
    }

    public static Label getSampleLengthLbl() {
        return sampleLengthLbl;
    }

    public static Label getActionInfoLbl() {
        return actionInfoLbl;
    }
}
