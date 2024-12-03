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
        view.updateBoard(model.getBoard());
        view.showMessage("Spiel gestartet! Spieler 1 (o) beginnt.");
        view.getSubmitButton().setOnAction(e -> handleInput());
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
                view.showMessage("Spieler " + model.getCurrentPlayer() + " gewinnt!");
                Platform.exit();
            } else if (model.isDraw()) {
                view.showMessage("Das Spiel endet unentschieden!");
                Platform.exit();
            } else {
                model.switchPlayer();
                view.showMessage("Spieler " + model.getCurrentPlayer() + " ist am Zug.");
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Bitte eine gültige Zahl eingeben.");
        } finally {
            view.getInputField().clear();
        }
    }
}