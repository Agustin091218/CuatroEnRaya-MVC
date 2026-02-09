package model;

public final class GameConstants {

    private GameConstants() {}

    // Tablero
    public static final int BOARD_ROWS = 6;
    public static final int BOARD_COLS = 7;
    public static final int WINNING_LENGTH = 4;

    // Identificadores
    public static final int PLAYER_ONE_ID = 1;
    public static final int PLAYER_TWO_ID = 2;
    public static final int EMPTY_CELL = 0;

    // Límites para lógica de victoria
    public static final int MAX_COL_FOR_WIN = 3;
    public static final int MAX_ROW_FOR_WIN = 2;
    public static final int MIN_ROW_FOR_DIAGONAL_WIN = 3;

    // Reglas de validación
    public static final int MAX_PLAYER_NAME_LENGTH = 20;

    // Comandos de Acción
    public static final String ACTION_LOGIN = "LOGIN";
    public static final String ACTION_NEW_GAME = "NEW_GAME";
    public static final String ACTION_EXIT = "EXIT";

    // UI Dimensiones
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 400;
    public static final int LOGIN_WINDOW_WIDTH = 500;
    public static final int LOGIN_WINDOW_HEIGHT = 350;
    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 35;

    // UI Fuentes
    public static final int FONT_SIZE_LARGE = 24;
    public static final int FONT_SIZE_MEDIUM = 20;
    public static final int FONT_SIZE_SMALL = 12;
    public static final String FONT_FAMILY = "Arial";

    // Textos
    public static final String GAME_TITLE = "4 en Raya - Juego en Progreso";
    public static final String LOGIN_TITLE = "Bienvenido - 4 en Raya";
    public static final String LOGIN_SUBTITLE = "INGRESO DE JUGADORES";
    public static final String TURN_LABEL_FORMAT = "Turno: %s";
    public static final String WINNER_MESSAGE_FORMAT = "¡GANADOR: %s!";
    public static final String COLUMN_FULL_MESSAGE = "¡Columna llena! Selecciona otra.";
    public static final String NEW_GAME_PROMPT = "¿Deseas jugar otra partida?";
    public static final String NEW_GAME_BUTTON_TEXT = "Nueva Partida";
    public static final String EXIT_BUTTON_TEXT = "Salir";
    public static final String LOGIN_BUTTON_TEXT = "COMENZAR JUEGO";
    public static final String PLAYER_1_LABEL = "Nombre Jugador 1:";
    public static final String PLAYER_2_LABEL = "Nombre Jugador 2:";
    public static final String DROP_ARROW = "▼";

    // Errores
    public static final String ERROR_PLAYER_NAME_EMPTY = "El nombre del jugador no puede ser nulo o estar vacío.";
    public static final String ERROR_PLAYER_NAME_TOO_LONG = "El nombre del jugador no puede exceder los 20 caracteres.";
    public static final String ERROR_PLAYER_NAME_HAS_NUMBERS = "El nombre del jugador no puede contener números.";
}
