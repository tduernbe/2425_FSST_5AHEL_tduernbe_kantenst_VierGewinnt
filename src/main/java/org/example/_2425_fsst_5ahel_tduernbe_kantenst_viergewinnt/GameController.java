package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private boolean gameStarted = false; // Status, ob das Spiel gestartet wurde
    private MinimaxAI ai; // KI-Instanz

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getStartButton().setOnAction(e -> startGame());
        view.updateBoard(model.getBoard()); // Spielfeld initialisieren
    }

    private void startGame() {
        // Überprüfen, ob beide Spielernamen eingegeben wurden
        String player1 = view.getPlayer1Field().getText().trim();
        String player2 = view.getPlayer2Field().getText().trim();

        if (player1.isEmpty() || player2.isEmpty()) {
            view.showMessage("Bitte geben Sie die Namen beider Spieler ein.");
            return;
        }

        // Spielernamen setzen
        model.setPlayerNames(player1, player2);

        // KI-Instanz erstellen (z. B. Spieler 2 ist die KI, die 'x' spielt)
        ai = new MinimaxAI('x', 'o');

        view.updateBoard(model.getBoard());
        view.showMessage("Spiel gestartet! " + model.getCurrentPlayerName() + " (o) beginnt.");

        // Spielstart
        gameStarted = true;
        enableColumnClickHandlers();
    }

    private void enableColumnClickHandlers() {
        // Alte Klick-Handler entfernen
        for (Node node : view.getBoardGrid().getChildren()) {
            node.setOnMouseClicked(null);
        }

        // Neue Klick-Handler setzen, wenn das Spiel gestartet wurde
        if (gameStarted) {
            for (int col = 0; col < model.getBoard()[0].length; col++) {
                int column = col; // Lokale Kopie für Lambda
                view.getBoardGrid().getChildren()
                        .filtered(node -> GridPane.getColumnIndex(node) == column)
                        .forEach(cell -> cell.setOnMouseClicked(e -> handleColumnClick(column)));
            }
        }
    }

    private void handleColumnClick(int col) {
        if (!gameStarted) {
            view.showMessage("Bitte starten Sie zuerst das Spiel.");
            return;
        }

        if (!model.makeMove(col)) {
            view.showMessage("Ungültiger Zug. Versuchen Sie es erneut.");
            return;
        }

        processMove();
    }

    private void processMove() {
        // Spielfeld aktualisieren
        view.updateBoard(model.getBoard());
        enableColumnClickHandlers();

        // Sieg oder Unentschieden überprüfen
        if (model.checkWin()) {
            view.showWinnerMessage(model.getCurrentPlayerName());
            gameStarted = false; // Spiel beenden
            return;
        }

        if (model.isDraw()) {
            view.showMessage("Das Spiel endet unentschieden!");
            Platform.exit();
            return;
        }

        // Spieler wechseln
        model.switchPlayer();

        if (model.getCurrentPlayerName().equals("KI")) {
            // KI-Zug
            int bestMove = ai.findBestMove(model.getBoard());
            model.makeMove(bestMove);
            view.showMessage("KI hat einen Zug in Spalte " + (bestMove + 1) + " gemacht.");
            processMove();
        } else {
            // Menschlicher Spieler ist am Zug
            view.showMessage(model.getCurrentPlayerName() + " ist am Zug.");
        }
    }
}
