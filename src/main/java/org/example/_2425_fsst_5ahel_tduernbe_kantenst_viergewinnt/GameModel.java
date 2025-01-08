package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;

import java.util.Arrays;

import java.util.Arrays;
import java.util.Random;

import java.util.Arrays;
import java.util.Random;

public class GameModel {
    private final char[][] board;
    private final char[] players = {'o', 'x'};
    private int currentPlayerIndex = 0;
    private String player1Name = "Spieler 1";
    private String player2Name = "Spieler 2";
    private boolean isVsComputer = false;
    private int difficultyLevel = 1; // 1: Leicht, 2: Mittel, 3: Schwer

    public GameModel(int rows, int cols) {
        this.board = new char[rows][cols];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public void setPlayerNames(String player1, String player2) {
        this.player1Name = player1;
        this.player2Name = player2;
    }

    public void setVsComputer(boolean isVsComputer, int difficultyLevel) {
        this.isVsComputer = isVsComputer;
        this.difficultyLevel = difficultyLevel;
    }

    public boolean isVsComputer() {
        return isVsComputer;
    }

    public String getCurrentPlayerName() {
        return currentPlayerIndex == 0 ? player1Name : player2Name;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean makeMove(int col) {
        col -= 1; // Anpassung auf 0-basierte Indizes
        if (col < 0 || col >= board[0].length) return false;
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = players[currentPlayerIndex];
                return true;
            }
        }
        return false;
    }

    public boolean checkWin() {
        int rows = board.length;
        int cols = board[0].length;
        char player = players[currentPlayerIndex];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == player &&
                        (checkDirection(row, col, 1, 0) || // Horizontal
                                checkDirection(row, col, 0, 1) || // Vertikal
                                checkDirection(row, col, 1, 1) || // Diagonal (rechts unten)
                                checkDirection(row, col, 1, -1))) { // Diagonal (links unten)
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol) {
        char player = players[currentPlayerIndex];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

    public boolean isDraw() {
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == ' ') return false;
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    public int getComputerMove() {
        if (difficultyLevel == 3) {
            return getStrategicMove(); // Schwer
        } else if (difficultyLevel == 2) {
            return getWinningOrBlockingMove(); // Mittel
        } else {
            return getRandomMove(); // Leicht
        }
    }

    private int getRandomMove() {
        Random random = new Random();
        int col;
        do {
            col = random.nextInt(board[0].length) + 1;
        } while (!canPlaceInColumn(col));
        return col;
    }

    private int getWinningOrBlockingMove() {
        for (int col = 1; col <= board[0].length; col++) {
            if (canPlaceInColumn(col)) {
                makeMove(col);
                if (checkWin()) {
                    undoMove(col);
                    return col;
                }
                undoMove(col);

                switchPlayer();
                makeMove(col);
                boolean opponentCanWin = checkWin();
                undoMove(col);
                switchPlayer();

                if (opponentCanWin) {
                    return col;
                }
            }
        }
        return getRandomMove();
    }

    private int getStrategicMove() {
        int centerColumn = board[0].length / 2 + 1;

        for (int col = 1; col <= board[0].length; col++) {
            if (canPlaceInColumn(col)) {
                makeMove(col);
                if (checkWin()) {
                    undoMove(col);
                    return col;
                }
                undoMove(col);
            }
        }

        for (int col = 1; col <= board[0].length; col++) {
            if (canPlaceInColumn(col)) {
                switchPlayer();
                makeMove(col);
                boolean opponentCanWin = checkWin();
                undoMove(col);
                switchPlayer();

                if (opponentCanWin) {
                    return col;
                }
            }
        }

        if (canPlaceInColumn(centerColumn)) {
            return centerColumn;
        }

        for (int col = 1; col <= board[0].length; col++) {
            if (canPlaceInColumn(col)) {
                return col;
            }
        }

        return getRandomMove();
    }

    private boolean canPlaceInColumn(int col) {
        col -= 1;
        return board[0][col] == ' ';
    }

    private void undoMove(int col) {
        col -= 1;
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] != ' ') {
                board[row][col] = ' ';
                break;
            }
        }
    }
}



