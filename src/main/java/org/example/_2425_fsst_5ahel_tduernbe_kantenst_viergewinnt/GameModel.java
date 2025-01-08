package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import java.util.Arrays;

public class GameModel {
    private final int rows;
    private final int cols;
    private final char[][] board;
    private final String[] playerNames = new String[2]; // Spieler-Namen
    private final char[] players = {'o', 'x'}; // Symbole der Spieler
    private int currentPlayerIndex = 0; // Aktueller Spielerindex

    public GameModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new char[rows][cols];
        for (char[] row : board) {
            Arrays.fill(row, ' '); // Leeres Spielfeld initialisieren
        }
    }

    public void setPlayerNames(String player1, String player2) {
        playerNames[0] = player1;
        playerNames[1] = player2;
    }

    public String getCurrentPlayerName() {
        return playerNames[currentPlayerIndex];
    }

    /**
     * Setzt einen Stein in der angegebenen Spalte.
     *
     * @param col Spaltenindex (0-basiert)
     * @return true, wenn der Zug erfolgreich war; false, wenn die Spalte voll ist oder ungültig.
     */
    public boolean makeMove(int col) {
        if (col < 0 || col >= cols) return false; // Ungültige Spalte
        for (int row = rows - 1; row >= 0; row--) { // Von unten nach oben durchgehen
            if (board[row][col] == ' ') { // Erste leere Zelle finden
                board[row][col] = players[currentPlayerIndex]; // Stein setzen
                return true;
            }
        }
        return false; // Spalte ist voll
    }

    /**
     * Überprüft, ob der aktuelle Spieler gewonnen hat.
     *
     * @return true, wenn der aktuelle Spieler gewonnen hat.
     */
    public boolean checkWin() {
        char currentPlayer = players[currentPlayerIndex];
        return checkDirection(0, 1, currentPlayer) || // Horizontal
                checkDirection(1, 0, currentPlayer) || // Vertikal
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

    /**
     * Überprüft, ob das Spiel unentschieden ist.
     *
     * @return true, wenn keine Züge mehr möglich sind.
     */
    public boolean isDraw() {
        for (int col = 0; col < cols; col++) {
            if (board[0][col] == ' ') return false; // Mindestens eine Spalte ist nicht voll
        }
        return true; // Alle Spalten sind voll
    }

    /**
     * Wechselt den aktuellen Spieler.
     */
    public void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex; // Wechsel zwischen 0 und 1
    }

    /**
     * Gibt das aktuelle Spielfeld zurück.
     *
     * @return 2D-Array des Spielfelds.
     */
    public char[][] getBoard() {
        return board;
    }
}
