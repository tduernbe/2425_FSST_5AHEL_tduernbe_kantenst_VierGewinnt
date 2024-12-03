package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameView {
    private final Stage stage;
    private final GridPane boardGrid = new GridPane();
    private final TextArea messageArea = new TextArea();
    private final TextField inputField = new TextField();
    private final Button submitButton = new Button("Zug spielen");

    public GameView(Stage stage) {
        this.stage = stage;
        VBox root = new VBox(10);
        messageArea.setEditable(false);
        inputField.setPromptText("Spaltennummer (0-6) eingeben");
        HBox inputBox = new HBox(10, inputField, submitButton);

        root.getChildren().addAll(boardGrid, messageArea, inputBox);

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Vier Gewinnt");
        stage.show();
    }

    public void updateBoard(char[][] board) {
        boardGrid.getChildren().clear();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Label cell = new Label(String.valueOf(board[row][col]));
                cell.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-min-width: 40; -fx-min-height: 40;");
                boardGrid.add(cell, col, row);
            }
        }
    }

    public void showMessage(String message) {
        messageArea.appendText(message + "\n");
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }
}
