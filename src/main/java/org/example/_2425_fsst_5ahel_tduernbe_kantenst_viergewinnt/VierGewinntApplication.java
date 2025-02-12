package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Application;
import javafx.stage.Stage;

public class VierGewinntApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setWidth(450); // Breite
        primaryStage.setHeight(900); // Höhe
        GameModel model = new GameModel(6, 7);
        GameView view = new GameView(primaryStage);
        new GameController(model, view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}