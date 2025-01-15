package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameView {
    private final Stage stage;
    private final GridPane boardGrid = new GridPane();
    private final TextArea messageArea = new TextArea();
    private final TextField player1Field = new TextField();
    private final TextField player2Field = new TextField();
    private final Button startButton = new Button("Spiel starten");
    private final ColorPicker player1ColorPicker = new ColorPicker(Color.RED);
    private final ColorPicker player2ColorPicker = new ColorPicker(Color.YELLOW);

    private final TitledPane boardPane = new TitledPane("Spielfeld", boardGrid);

    private Color player1Color = Color.RED;
    private Color player2Color = Color.YELLOW;

    public GameView(Stage stage) {
        this.stage = stage;

        VBox root = new VBox(10);
        messageArea.setEditable(false);
        player1Field.setPromptText("Name Spieler 1 (o)");
        player2Field.setPromptText("Name Spieler 2 (x)");

        // Farbwähler für Spieler hinzufügen
        HBox player1Box = new HBox(10, new Label("Farbe Spieler 1:"), player1ColorPicker, player1Field);
        HBox player2Box = new HBox(10, new Label("Farbe Spieler 2:"), player2ColorPicker, player2Field);


        VBox playerInputBox = new VBox(10, player1Box, player2Box, startButton);

        // Neuen TitledPane für die Spielereinstellungen erstellen
        TitledPane playerSettingsPane = new TitledPane("Spielereinstellungen", playerInputBox);
        playerSettingsPane.setCollapsible(true);
        playerSettingsPane.setExpanded(true); // Standardmäßig aufgeklappt

        // Spielfeld einklappbar machen
        boardPane.setCollapsible(true);
        boardPane.setExpanded(true);
        boardPane.setStyle("-fx-padding: 10;");

        // Root-Layout
        root.getChildren().addAll(playerSettingsPane, boardPane, messageArea);

        Scene scene = new Scene(root, 600, 800);
        stage.setScene(scene);
        stage.setTitle("Vier Gewinnt");
        stage.show();

        // Farbwähler-Ereignisbehandlung
        player1ColorPicker.setOnAction(e -> player1Color = player1ColorPicker.getValue());
        player2ColorPicker.setOnAction(e -> player2Color = player2ColorPicker.getValue());
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
                    cell.getChildren().add(createCircle(player1Color));
                } else if (stone == 'x') {
                    cell.getChildren().add(createCircle(player2Color));
                }

                boardGrid.add(cell, col, row);
            }
        }
    }

    private Circle createCircle(Color color) {
        Circle circle = new Circle(20, color);
        circle.setCenterX(25);
        circle.setCenterY(25);
        return circle;
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

    public Button getStartButton() {
        return startButton;
    }

    public TextField getPlayer1Field() {
        return player1Field;
    }

    public TextField getPlayer2Field() {
        return player2Field;
    }

    public GridPane getBoardGrid() {
        return boardGrid;
    }
}


