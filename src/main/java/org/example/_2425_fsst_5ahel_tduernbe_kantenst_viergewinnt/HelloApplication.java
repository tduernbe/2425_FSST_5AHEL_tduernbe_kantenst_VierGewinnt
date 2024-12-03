package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class VierGewinntApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameModel model = new GameModel(6, 7);
        GameView view = new GameView(primaryStage);
        new GameController(model, view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}