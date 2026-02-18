package model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameModel {

    private Player playerOne;
    private Player playerTwo;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int[][] board;
    private int winnerId;
    private boolean draw;

    public GameModel() {
        resetGame();
    }

    /**
     * Crea los jugadores validando sus nombres.
     * Delega la validacion individual a Player, y valida reglas entre jugadores aca.
     */
    public void createPlayers(String nameOne, String nameTwo) {
        if (nameOne == null || nameOne.trim().isEmpty()) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_EMPTY);
        }
        if (nameTwo == null || nameTwo.trim().isEmpty()) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_EMPTY);
        }
        if (nameOne.equalsIgnoreCase(nameTwo)) {
            throw new IllegalArgumentException(GameConstants.ERROR_DUPLICATE_PLAYER_NAMES);
        }

        playerOne = new Player(nameOne, GameConstants.PLAYER_ONE_ID);
        playerTwo = new Player(nameTwo, GameConstants.PLAYER_TWO_ID);

        players.clear();
        players.add(playerOne);
        players.add(playerTwo);

        currentPlayer = playerOne;
        resetGame();
    }

    public void resetGame() {
        board = new int[GameConstants.BOARD_ROWS][GameConstants.BOARD_COLS];
        winnerId = GameConstants.EMPTY_CELL;
        draw = false;
        if (!players.isEmpty()) {
            currentPlayer = players.get(0);
        }
    }

    // Logica del juego

    public boolean tryPlayMove(int column) {
        if (column < 0 || column >= GameConstants.BOARD_COLS) {
            return false;
        }

        for (int row = GameConstants.BOARD_ROWS - 1; row >= 0; row--) {
            if (board[row][column] == GameConstants.EMPTY_CELL) {
                board[row][column] = currentPlayer.getId();
                return true;
            }
        }

        return false;
    }

    public void processGameState() {
        winnerId = GameRules.checkWinner(board);

        if (winnerId != GameConstants.EMPTY_CELL) {
            return;
        }

        if (GameRules.isBoardFull(board)) {
            draw = true;
            return;
        }

        switchTurn();
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == players.get(0))
                ? players.get(1)
                : players.get(0);
    }

    // Estado del juego

    public boolean isGameOver() {
        return winnerId != GameConstants.EMPTY_CELL || draw;
    }

    public boolean isDraw() {
        return draw;
    }

    public String getCurrentPlayerName() {
        return (currentPlayer != null) ? currentPlayer.getName() : "Desconocido";
    }

    public String getWinnerName() {
        if (winnerId == GameConstants.PLAYER_ONE_ID && playerOne != null) {
            return playerOne.getName();
        }
        if (winnerId == GameConstants.PLAYER_TWO_ID && playerTwo != null) {
            return playerTwo.getName();
        }
        return null;
    }

    // Copia defensiva: nadie externo puede mutar el tablero del modelo
    public int[][] getBoard() {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    // Getters de dimensiones para que el Controlador pueda informar a la Vista
    public int getBoardRows() {
        return GameConstants.BOARD_ROWS;
    }

    public int getBoardCols() {
        return GameConstants.BOARD_COLS;
    }
}
