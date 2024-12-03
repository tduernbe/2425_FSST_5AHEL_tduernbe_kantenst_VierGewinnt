package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Platform;

public class GameController {
    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getStartButton().setOnAction(e -> startGame());
        view.getSubmitButton().setOnAction(e -> handleInput());
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
    }

    private void handleInput() {
        try {
            int col = Integer.parseInt(view.getInputField().getText());
            if (!model.makeMove(col)) {
                view.showMessage("Ungültiger Zug. Versuche es erneut.");
                return;
            }
            view.updateBoard(model.getBoard());
            if (model.checkWin()) {
                view.showWinnerMessage(model.getCurrentPlayerName());
            } else if (model.isDraw()) {
                view.showMessage("Das Spiel endet unentschieden!");
                Platform.exit();
            } else {
                model.switchPlayer();
                view.showMessage(model.getCurrentPlayerName() + " ist am Zug.");
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Bitte eine gültige Zahl eingeben.");
        } finally {
            view.getInputField().clear();
        }
    }
}
