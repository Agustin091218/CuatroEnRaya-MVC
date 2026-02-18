package model;

public final class GameConstants {

    private GameConstants() {}

    // Tablero
    public static final int BOARD_ROWS = 6;
    public static final int BOARD_COLS = 7;
    public static final int WINNING_LENGTH = 4;

    // Identificadores de jugador
    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;
    public static final int EMPTY_CELL = 0;

    // Limites para logica de victoria
    public static final int MAX_COL_FOR_WIN = BOARD_COLS - WINNING_LENGTH;
    public static final int MAX_ROW_FOR_WIN = BOARD_ROWS - WINNING_LENGTH;
    public static final int MIN_ROW_FOR_DIAGONAL_WIN = WINNING_LENGTH - 1;

    // Reglas de validacion de jugadores
    public static final int MAX_PLAYER_NAME_LENGTH = 20;

    // Mensajes de error de dominio (validacion de negocio)
    public static final String ERROR_PLAYER_NAME_EMPTY = "El nombre del jugador no puede ser nulo o estar vacio.";
    public static final String ERROR_PLAYER_NAME_TOO_LONG = "El nombre del jugador no puede exceder los 20 caracteres.";
    public static final String ERROR_PLAYER_NAME_HAS_NUMBERS = "El nombre del jugador no puede contener numeros.";
    public static final String ERROR_DUPLICATE_PLAYER_NAMES = "Los jugadores no pueden tener el mismo nombre.";
}
