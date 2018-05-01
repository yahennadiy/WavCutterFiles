package samplecutter;

import javafx.application.Application;
import javafx.stage.Stage;
import samplecutter.config.ConfigReader;
import samplecutter.fxapplication.Controller;
import samplecutter.fxapplication.mainscene.MainSceneCreator;

public class Main extends Application {
    public static void main(String[] args) {
        ConfigReader.exec();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Wav Sample Cutter   ".concat(Character.toString((char) 169)).concat(" yahennadiy"));
        Controller.init();
        stage.setScene(MainSceneCreator.getMainScene());
        MainSceneCreator.getFilesNamesLv().getSelectionModel().clearSelection();
        MainSceneCreator.getCheckFileBtn().setDisable(true);
        MainSceneCreator.getSampleBitsRemainLv().setDisable(true);
        MainSceneCreator.getExecuteBtn().setDisable(true);
        stage.show();
    }
}
