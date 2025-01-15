Programmiere mir für die folgende VierGewinnt Anwendung die Benutzeroberfläche:
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

package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
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
package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import java.util.Arrays;

public class GameModel {
private final int rows;
private final int cols;
private final char[][] board;
private final char[] players = {'o', 'x'};
private int currentPlayerIndex = 0;

    public GameModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new char[rows][cols];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public boolean makeMove(int col) {
        if (col < 0 || col >= cols) return false;
        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = players[currentPlayerIndex];
                return true;
            }
        }
        return false;
    }

    public boolean checkWin() {
        char currentPlayer = players[currentPlayerIndex];
        // Check horizontal, vertical, and diagonal
        return checkDirection(0, 1, currentPlayer) || // Horizontal
                checkDirection(1, 0, currentPlayer) || // Vertical
                checkDirection(1, 1, currentPlayer) || // Diagonal /
                checkDirection(1, -1, currentPlayer);  // Diagonal \
    }

    private boolean checkDirection(int dRow, int dCol, char player) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (isWinningSequence(row, col, dRow, dCol, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinningSequence(int row, int col, int dRow, int dCol, char player) {
        for (int i = 0; i < 4; i++) {
            int r = row + i * dRow, c = col + i * dCol;
            if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != player) {
                return false;
            }
        }
        return true;
    }

    public boolean isDraw() {
        for (int col = 0; col < cols; col++) {
            if (board[0][col] == ' ') return false;
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return players[currentPlayerIndex];
    }
}

Mit welcher Funktion kann ein Teil der VBOX einklappbar werden?