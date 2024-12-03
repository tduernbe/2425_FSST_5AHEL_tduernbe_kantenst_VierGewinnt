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
    private final TextField player1Field = new TextField();
    private final TextField player2Field = new TextField();
    private final Button startButton = new Button("Spiel starten");
    private final Label currentPlayerLabel = new Label("Am Zug: ");

    public GameView(Stage stage) {
        this.stage = stage;
        VBox root = new VBox(10);
        messageArea.setEditable(false);
        inputField.setPromptText("Spaltennummer (1-7) eingeben");
        player1Field.setPromptText("Name Spieler 1 (o)");
        player2Field.setPromptText("Name Spieler 2 (x)");
        HBox inputBox = new HBox(10, inputField, submitButton);

        VBox playerInputBox = new VBox(10, player1Field, player2Field, startButton);
        currentPlayerLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: darkblue;");
        root.getChildren().addAll(playerInputBox, currentPlayerLabel, boardGrid, messageArea, inputBox);

        Scene scene = new Scene(root, 500, 700);
        stage.setScene(scene);
        stage.setTitle("Vier Gewinnt");
        stage.show();
    }

    public void updateBoard(char[][] board) {
        boardGrid.getChildren().clear();
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);
        boardGrid.setStyle("-fx-padding: 20; -fx-background-color: lightblue; -fx-border-color: navy; -fx-border-width: 3;");

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Pane cell = new Pane();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");

                char stone = board[row][col];
                if (stone == 'o') {
                    cell.setStyle("-fx-background-color: red; -fx-border-radius: 25; -fx-background-radius: 25;");
                } else if (stone == 'x') {
                    cell.setStyle("-fx-background-color: yellow; -fx-border-radius: 25; -fx-background-radius: 25;");
                }

                boardGrid.add(cell, col, row);
            }
        }
    }

    public void updateCurrentPlayer(String playerName) {
        currentPlayerLabel.setText("Am Zug: " + playerName);
    }

    public void showMessage(String message) {
        messageArea.appendText(message + "\n");
    }

    public void showWinnerMessage(String winnerName) {
        Stage winnerStage = new Stage();
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label winnerLabel = new Label("Herzlichen Glückwunsch, " + winnerName + "! Du hast gewonnen!");
        winnerLabel.setStyle("-fx-font-size: 18; -fx-text-fill: green; -fx-alignment: center;");
        Button closeButton = new Button("Spiel beenden");
        closeButton.setOnAction(e -> System.exit(0));

        layout.getChildren().addAll(winnerLabel, closeButton);
        Scene scene = new Scene(layout);
        winnerStage.setTitle("Spiel beendet");
        winnerStage.setScene(scene);
        winnerStage.show();
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public TextField getPlayer1Field() {
        return player1Field;
    }

    public TextField getPlayer2Field() {
        return player2Field;
    }

    public Button getStartButton() {
        return startButton;
    }
}
