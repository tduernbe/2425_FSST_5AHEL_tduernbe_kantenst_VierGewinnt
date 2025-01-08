package org.example._2425_fsst_5ahel_tduernbe_kantenst_viergewinnt;


public class MinimaxAI {
    private final char aiPlayer;  // Symbol der KI ('x' oder 'o')
    private final char humanPlayer;  // Symbol des menschlichen Spielers ('x' oder 'o')

    public MinimaxAI(char aiPlayer, char humanPlayer) {
        this.aiPlayer = aiPlayer;
        this.humanPlayer = humanPlayer;
    }

    /**
     * Findet den besten Zug für die KI mit Minimax.
     *
     * @param board Das aktuelle Spielfeld.
     * @return Die beste Spalte, in die die KI einen Stein setzen sollte.
     */
    public int findBestMove(char[][] board) {
        int bestValue = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int col = 0; col < board[0].length; col++) {
            if (isValidMove(board, col)) {
                int row = makeMove(board, col, aiPlayer);
                int moveValue = minimax(board, 5, false); // Tiefe: 5
                undoMove(board, row, col);

                if (moveValue > bestValue) {
                    bestValue = moveValue;
                    bestMove = col;
                }
            }
        }

        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (checkWin(board, aiPlayer)) return 1000 - depth;
        if (checkWin(board, humanPlayer)) return -1000 + depth;
        if (isDraw(board) || depth == 0) return 0;

        int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int col = 0; col < board[0].length; col++) {
            if (isValidMove(board, col)) {
                int row = makeMove(board, col, isMaximizing ? aiPlayer : humanPlayer);
                int value = minimax(board, depth - 1, !isMaximizing);
                undoMove(board, row, col);

                if (isMaximizing) {
                    bestValue = Math.max(bestValue, value);
                } else {
                    bestValue = Math.min(bestValue, value);
                }
            }
        }

        return bestValue;
    }

    private boolean isValidMove(char[][] board, int col) {
        return board[0][col] == ' ';
    }

    private int makeMove(char[][] board, int col, char player) {
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = player;
                return row;
            }
        }
        return -1; // Sollte nie passieren
    }

    private void undoMove(char[][] board, int row, int col) {
        board[row][col] = ' ';
    }

    private boolean checkWin(char[][] board, char player) {
        // Ähnlich wie in GameModel.checkWin
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (isWinningSequence(board, row, col, 0, 1, player) || // Horizontal
                        isWinningSequence(board, row, col, 1, 0, player) || // Vertikal
                        isWinningSequence(board, row, col, 1, 1, player) || // Diagonal /
                        isWinningSequence(board, row, col, 1, -1, player)) { // Diagonal \
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinningSequence(char[][] board, int row, int col, int dRow, int dCol, char player) {
        for (int i = 0; i < 4; i++) {
            int r = row + i * dRow, c = col + i * dCol;
            if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean isDraw(char[][] board) {
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == ' ') return false;
        }
        return true;
    }
}

