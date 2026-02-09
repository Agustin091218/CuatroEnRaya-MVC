package model;

import java.util.ArrayList;

public class GameModel {
    private Player player1;
    private Player player2;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int[][] board;
    private int winnerId;

    public GameModel() {
        resetGame();
    }

    /**
     * Crea los jugadores validando sus nombres.
     * @throws IllegalArgumentException Si los nombres no cumplen las reglas.
     */
    public void createPlayers(String name1, String name2) {
        if (name1 == null || name1.trim().isEmpty()) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_EMPTY);
        }
        if (name2 == null || name2.trim().isEmpty()) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_EMPTY);
        }
        if (name1.length() > GameConstants.MAX_PLAYER_NAME_LENGTH ||
            name2.length() > GameConstants.MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(GameConstants.ERROR_PLAYER_NAME_TOO_LONG);
        }
        // Valida que no se llamen igual
        if (name1.equalsIgnoreCase(name2)) {
            throw new IllegalArgumentException("Los jugadores no pueden tener el mismo nombre.");
        }

        player1 = new Player(name1, GameConstants.PLAYER_ONE_ID);
        player2 = new Player(name2, GameConstants.PLAYER_TWO_ID);

        players.clear();
        players.add(player1);
        players.add(player2);

        // El jugador 1 siempre empieza
        this.currentPlayer = player1;

        resetGame();
    }

    public void resetGame() {
        board = new int[GameConstants.BOARD_ROWS][GameConstants.BOARD_COLS];
        winnerId = GameConstants.EMPTY_CELL;
        // Si ya existen jugadores, reseteamos al primero. Si no, queda null hasta createPlayers.
        if (!players.isEmpty()) {
            currentPlayer = players.get(0);
        }
    }

    //Logica del juego

    public boolean tryPlayMove(int column) {
        // Verificamos que la columna sea válida
        if (column < 0 || column >= GameConstants.BOARD_COLS) {
            return false;
        }

        // Busca la primera celda vacía desde abajo hacia arriba
        for (int row = GameConstants.BOARD_ROWS - 1; row >= 0; row--) {
            if (board[row][column] == GameConstants.EMPTY_CELL) {
                // Colocar la ficha del jugador actual
                board[row][column] = currentPlayer.getId();
                return true;
            }
        }

        // Si la columna está llena
        return false;
    }

    public void processGameState() {
        // Delega la verificacin de ganador a la clase GameRules
        winnerId = GameRules.checkWinner(board);

        // cambiamos el turno
        if (winnerId == GameConstants.EMPTY_CELL) {
            switchTurn();
        }
    }

    private void switchTurn() {
        // Operador ternario para alternar entre jugador 1 y 2
        currentPlayer = (currentPlayer == players.get(0)) ? players.get(1) : players.get(0);
    }

    public boolean isGameOver() {
        return winnerId != GameConstants.EMPTY_CELL;
    }

    public String getCurrentPlayerName() {
        // Protección contra NullPointerException si se llama antes de crear jugadores
        return (currentPlayer != null) ? currentPlayer.getName() : "Desconocido";
    }

    public int[][] getBoard() {
        return board;
    }

    public String getWinnerName() {
        if (winnerId == GameConstants.PLAYER_ONE_ID) return player1.getName();
        if (winnerId == GameConstants.PLAYER_TWO_ID) return player2.getName();
        return null;
    }
}
