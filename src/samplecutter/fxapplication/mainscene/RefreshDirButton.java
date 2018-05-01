package samplecutter.fxapplication.mainscene;

import javafx.scene.control.Button;

public class RefreshDirButton {
    static Button create() {
        Button refreshDirBtn = new Button("Refresh Directory");
        refreshDirBtn.setOnAction(actionEvent -> {
            MainSceneCreator.refresh();
        });
        return refreshDirBtn;
    }
}
