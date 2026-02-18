package view;

import java.awt.Color;

public class ColorScheme {

    private static final Color DEFAULT_PLAYER_ONE_COLOR = Color.WHITE;
    private static final Color DEFAULT_PLAYER_TWO_COLOR = new Color(220, 20, 60);
    private static final Color DEFAULT_EMPTY_CELL_COLOR = new Color(100, 100, 100);
    private static final Color DEFAULT_BOARD_BACKGROUND_COLOR = new Color(30, 30, 50);
    private static final Color DEFAULT_BUTTON_BACKGROUND_COLOR = new Color(40, 40, 60);
    private static final Color DEFAULT_BORDER_COLOR = new Color(50, 50, 80);
    private static final Color DEFAULT_BUTTON_BORDER_COLOR = new Color(80, 80, 100);
    private static final Color DEFAULT_CONTROL_BUTTON_COLOR = new Color(75, 110, 175);
    private static final Color DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final Color DEFAULT_CYAN_COLOR = Color.CYAN;
    private static final Color DEFAULT_ORANGE_COLOR = Color.ORANGE;
    private static final Color DEFAULT_LOGIN_BACKGROUND_COLOR = new Color(60, 63, 65);

    private final Color playerOneColor;
    private final Color playerTwoColor;
    private final Color emptyCellColor;
    private final Color boardBackgroundColor;
    private final Color buttonBackgroundColor;

    public ColorScheme() {
        this(DEFAULT_PLAYER_ONE_COLOR, DEFAULT_PLAYER_TWO_COLOR,
             DEFAULT_EMPTY_CELL_COLOR, DEFAULT_BOARD_BACKGROUND_COLOR,
             DEFAULT_BUTTON_BACKGROUND_COLOR);
    }

    public ColorScheme(Color playerOneColor, Color playerTwoColor,
                       Color emptyCellColor, Color boardBackgroundColor,
                       Color buttonBackgroundColor) {
        this.playerOneColor = playerOneColor;
        this.playerTwoColor = playerTwoColor;
        this.emptyCellColor = emptyCellColor;
        this.boardBackgroundColor = boardBackgroundColor;
        this.buttonBackgroundColor = buttonBackgroundColor;
    }

    public Color getPlayerColor(int playerId) {
        return switch (playerId) {
            case 1 -> playerOneColor;
            case 2 -> playerTwoColor;
            default -> emptyCellColor;
        };
    }

    public Color getEmptyCellColor() {
        return emptyCellColor;
    }

    public Color getBoardBackgroundColor() {
        return boardBackgroundColor;
    }

    public Color getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public Color getBorderColor() {
        return DEFAULT_BORDER_COLOR;
    }

    public Color getButtonBorderColor() {
        return DEFAULT_BUTTON_BORDER_COLOR;
    }

    public Color getControlButtonColor() {
        return DEFAULT_CONTROL_BUTTON_COLOR;
    }

    public Color getTextColor() {
        return DEFAULT_TEXT_COLOR;
    }

    public Color getCyanColor() {
        return DEFAULT_CYAN_COLOR;
    }

    public Color getOrangeColor() {
        return DEFAULT_ORANGE_COLOR;
    }

    public Color getLoginBackgroundColor() {
        return DEFAULT_LOGIN_BACKGROUND_COLOR;
    }
}
