package model;
public final class GameRules {
    private GameRules() {}
    // Método para verificar si hay un ganador en el tablero
    public static int checkWinner(int[][] board) {
        for (int row = 0; row < GameConstants.BOARD_ROWS; row++) {
            for (int col = 0; col < GameConstants.BOARD_COLS; col++) {
                int token = board[row][col];
                if (token == GameConstants.EMPTY_CELL) continue;

                if (checkHorizontalWin(board, row, col, token) ||
                    checkVerticalWin(board, row, col, token) ||
                    checkDiagonalDownWin(board, row, col, token) ||
                    checkDiagonalUpWin(board, row, col, token)) {
                    return token;
                }
            }
        }
        return GameConstants.EMPTY_CELL;
    }
   // Métodos privados para verificar cada tipo de victoria (horizontal, vertical, diagonal hacia abajo y diagonal hacia arriba)
    private static boolean checkHorizontalWin(int[][] board, int row, int col, int token) {
        if (col > GameConstants.MAX_COL_FOR_WIN) return false;

        for (int i = 1; i < GameConstants.WINNING_LENGTH; i++) {
            if (board[row][col + i] != token) return false;
        }
        return true;
    }

    private static boolean checkVerticalWin(int[][] board, int row, int col, int token) {
        if (row > GameConstants.MAX_ROW_FOR_WIN) return false;

        for (int i = 1; i < GameConstants.WINNING_LENGTH; i++) {
            if (board[row + i][col] != token) return false;
        }
        return true;
    }

    private static boolean checkDiagonalDownWin(int[][] board, int row, int col, int token) {
        if (row > GameConstants.MAX_ROW_FOR_WIN || col > GameConstants.MAX_COL_FOR_WIN) return false;

        for (int i = 1; i < GameConstants.WINNING_LENGTH; i++) {
            if (board[row + i][col + i] != token) return false;
        }
        return true;
    }

    private static boolean checkDiagonalUpWin(int[][] board, int row, int col, int token) {
        if (row < GameConstants.MIN_ROW_FOR_DIAGONAL_WIN || col > GameConstants.MAX_COL_FOR_WIN) return false;

        for (int i = 1; i < GameConstants.WINNING_LENGTH; i++) {
            if (board[row - i][col + i] != token) return false;
        }
        return true;
    }

    public static boolean isValidMove(int column, int[][] board) {
        if (column < 0 || column >= GameConstants.BOARD_COLS) return false;
        return board[0][column] == GameConstants.EMPTY_CELL;
    }

    public static boolean isBoardFull(int[][] board) {
        for (int col = 0; col < GameConstants.BOARD_COLS; col++) {
            if (board[0][col] == GameConstants.EMPTY_CELL) return false;
        }
        return true;
    }
}
