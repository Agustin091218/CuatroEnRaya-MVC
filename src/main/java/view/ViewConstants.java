package view;

public final class ViewConstants {

    private ViewConstants() {}

    // Dimensiones de ventana
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 400;
    public static final int LOGIN_WINDOW_WIDTH = 500;
    public static final int LOGIN_WINDOW_HEIGHT = 350;
    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 35;

    // Fuentes
    public static final int FONT_SIZE_LARGE = 24;
    public static final int FONT_SIZE_MEDIUM = 20;
    public static final int FONT_SIZE_SMALL = 12;
    public static final String FONT_FAMILY = "Arial";

    // Comandos de accion (contrato Vista <-> Controlador)
    public static final String ACTION_LOGIN = "LOGIN";
    public static final String ACTION_NEW_GAME = "NEW_GAME";
    public static final String ACTION_EXIT = "EXIT";

    // Textos de la interfaz
    public static final String GAME_TITLE = "4 en Raya - Juego en Progreso";
    public static final String LOGIN_TITLE = "Bienvenido - 4 en Raya";
    public static final String LOGIN_SUBTITLE = "INGRESO DE JUGADORES";
    public static final String TURN_LABEL_FORMAT = "Turno: %s";
    public static final String WINNER_MESSAGE_FORMAT = "GANADOR: %s!";
    public static final String DRAW_MESSAGE = "Empate! El tablero esta lleno.";
    public static final String COLUMN_FULL_MESSAGE = "Columna llena! Selecciona otra.";
    public static final String NEW_GAME_PROMPT = "Deseas jugar otra partida?";
    public static final String NEW_GAME_BUTTON_TEXT = "Nueva Partida";
    public static final String EXIT_BUTTON_TEXT = "Salir";
    public static final String LOGIN_BUTTON_TEXT = "COMENZAR JUEGO";
    public static final String PLAYER_1_LABEL = "Nombre Jugador 1:";
    public static final String PLAYER_2_LABEL = "Nombre Jugador 2:";
    public static final String DROP_ARROW = "\u25BC";
}
