package samplecutter.fxapplication.mainscene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import samplecutter.fxapplication.Controller;

public class SampleBitsRemainListView {
    private static String depth;

    static ListView<String> create(String[] bitsRemainArr) {
        ObservableList<String> filesNameObsList =
                FXCollections.observableArrayList(bitsRemainArr);
        ListView<String> sampleBitsRemainLv = new ListView<String>(filesNameObsList);
        sampleBitsRemainLv.setPrefWidth(Controller.getSampleBitsRemainLvWidth());
        MultipleSelectionModel<String> lvSelModelFiles =
                sampleBitsRemainLv.getSelectionModel();
        lvSelModelFiles.selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    int dep;
                    for (int i = 0; i < bitsRemainArr.length; i++) {
                        if (bitsRemainArr[i].equals(newValue)) {
                            dep = i;
                            depth = Integer.toString(dep);
                            if (dep < 10) {
                                depth = "0" + depth;
                            }

                            break;
                        }
                    }

                    MainSceneCreator.getSampleLengthLbl().setText((new StringBuilder()).
                            append("The music information in the sample will move up to: ").
                            append(depth).append(" bits").toString());
                    MainSceneCreator.getExecuteBtn().setDisable(false);
                }
        );
        return sampleBitsRemainLv;
    }

    public static String getDepth() {
        return depth;
    }
}
