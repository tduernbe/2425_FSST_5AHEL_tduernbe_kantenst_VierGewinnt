package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import java.awt.Toolkit;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private boolean gameStarted = false;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getStartButton().setOnAction(e -> startGame());
        view.updateBoard(model.getBoard());
    }

    private void startGame() {
        String player1 = view.getPlayer1Field().getText().trim();
        String player2 = view.getPlayer2Field().getText().trim();

        if (player1.isEmpty() || player2.isEmpty()) {
            view.showMessage("Bitte geben Sie die Namen beider Spieler ein.");
            return;
        }

        model.setPlayerNames(player1, player2);
        view.updateBoard(model.getBoard());
        view.showMessage("Spiel gestartet! " + model.getCurrentPlayerName() + " (o) beginnt.");
        gameStarted = true;
        enableColumnClickHandlers();
    }

    private void enableColumnClickHandlers() {
        for (Node node : view.getBoardGrid().getChildren()) {
            node.setOnMouseClicked(null);
        }

        if (gameStarted) {
            for (int col = 0; col < model.getBoard()[0].length; col++) {
                int column = col;
                view.getBoardGrid().getChildren()
                        .filtered(node -> GridPane.getColumnIndex(node) == column)
                        .forEach(cell -> cell.setOnMouseClicked(e -> handleColumnClick(column)));
            }
        }
    }

    private void handleColumnClick(int col) {
        if (!gameStarted) {
            view.showMessage("Bitte starten Sie zuerst das Spiel, bevor Sie einen Zug machen.");
            return;
        }

        try {
            if (!model.makeMove(col)) {
                playErrorSound(); // Warnton
                view.showMessage("Ungültiger Zug. Versuche es erneut.");
                return;
            }

            view.updateBoard(model.getBoard());
            enableColumnClickHandlers();

            if (model.checkWin()) {
                view.showWinnerMessage(model.getCurrentPlayerName());
            } else if (model.isDraw()) {
                view.showMessage("Das Spiel endet unentschieden!");
                Platform.exit();
            } else {
                model.switchPlayer();
                view.showMessage(model.getCurrentPlayerName() + " ist am Zug.");
            }
        } catch (Exception ex) {
            view.showMessage("Ein Fehler ist aufgetreten: " + ex.getMessage());
        }
    }

    private void playErrorSound() {
        Toolkit.getDefaultToolkit().beep();
    }
}

