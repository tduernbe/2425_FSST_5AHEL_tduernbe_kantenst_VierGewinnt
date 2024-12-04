package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private boolean gameStarted = false;  // Spielstatus, ob das Spiel bereits gestartet ist

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        // Der Start-Button bleibt aktiv, aber wir starten das Spiel, wenn er gedrückt wird
        view.getStartButton().setOnAction(e -> startGame());
        view.updateBoard(model.getBoard()); // Initiales Board setzen
    }

    private void startGame() {
        // Überprüfen, ob beide Spielernamen eingegeben wurden
        String player1 = view.getPlayer1Field().getText().trim();
        String player2 = view.getPlayer2Field().getText().trim();

        if (player1.isEmpty() || player2.isEmpty()) {
            view.showMessage("Bitte geben Sie die Namen beider Spieler ein.");
            return;
        }

        // Setzen der Spielernamen
        model.setPlayerNames(player1, player2);
        view.updateBoard(model.getBoard());
        view.showMessage("Spiel gestartet! " + model.getCurrentPlayerName() + " (o) beginnt.");

        // Spielstart abgeschlossen, daher erlauben wir Klicks für Züge
        gameStarted = true;
        enableColumnClickHandlers(); // Klick-Handler für Spalten setzen
    }

    private void enableColumnClickHandlers() {
        // Entfernen der alten Klick-Handler (falls vorhanden)
        for (Node node : view.getBoardGrid().getChildren()) {
            node.setOnMouseClicked(null);
        }

        // Setzen der neuen Klick-Handler, wenn das Spiel gestartet wurde
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
        // Überprüfen, ob das Spiel gestartet ist, andernfalls starten
        if (!gameStarted) {
            startGame();
        }

        try {
            // Einen Zug machen und prüfen, ob er gültig ist
            if (!model.makeMove(col)) {
                view.showMessage("Ungültiger Zug. Versuche es erneut.");
                return;
            }

            view.updateBoard(model.getBoard());

            // Überprüfen, ob der aktuelle Spieler gewonnen hat
            if (model.checkWin()) {
                view.showWinnerMessage(model.getCurrentPlayerName());
            } else if (model.isDraw()) {
                view.showMessage("Das Spiel endet unentschieden!");
                Platform.exit();  // Beenden, falls unentschieden
            } else {
                // Spieler wechseln
                model.switchPlayer();
                view.showMessage(model.getCurrentPlayerName() + " ist am Zug.");
            }
        } catch (Exception ex) {
            view.showMessage("Ein Fehler ist aufgetreten: " + ex.getMessage());
        }
    }
}


