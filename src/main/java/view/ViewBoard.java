package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista principal del juego. Muestra el tablero, botones de control y estado.
 * No importa nada del modelo: recibe dimensiones por constructor,
 * y datos del juego via metodos que invoca el Controlador.
 */
public class ViewBoard extends JFrame {

    private final int rows;
    private final int cols;
    private final ColorScheme colorScheme;

    private JButton[] columnButtons;
    private JButton[][] boardCells;
    private JLabel turnLabel;
    private JButton newGameButton;
    private JButton exitButton;

    public ViewBoard(int rows, int cols) {
        this(rows, cols, new ColorScheme());
    }

    public ViewBoard(int rows, int cols, ColorScheme colorScheme) {
        super(ViewConstants.GAME_TITLE);
        this.rows = rows;
        this.cols = cols;
        this.colorScheme = colorScheme;

        setSize(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
    }

    // Panel superior: turno + botones de control
    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorScheme.getButtonBackgroundColor());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        turnLabel = new JLabel();
        turnLabel.setFont(new Font(ViewConstants.FONT_FAMILY, Font.BOLD, ViewConstants.FONT_SIZE_LARGE));
        turnLabel.setForeground(colorScheme.getTextColor());
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(turnLabel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        controlPanel.setBackground(colorScheme.getButtonBackgroundColor());

        newGameButton = createStyledButton(ViewConstants.NEW_GAME_BUTTON_TEXT);
        exitButton = createStyledButton(ViewConstants.EXIT_BUTTON_TEXT);
        controlPanel.add(newGameButton);
        controlPanel.add(exitButton);

        topPanel.add(controlPanel, BorderLayout.EAST);
        return topPanel;
    }

    // Panel central: flechas de columna + grilla del tablero
    private JPanel buildCenterPanel() {
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(colorScheme.getBoardBackgroundColor());
        centerContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        centerContainer.add(buildDropButtonPanel(), BorderLayout.NORTH);
        centerContainer.add(buildBoardPanel(), BorderLayout.CENTER);
        return centerContainer;
    }

    private JPanel buildDropButtonPanel() {
        JPanel dropPanel = new JPanel(new GridLayout(1, cols, 5, 0));
        dropPanel.setBackground(colorScheme.getBoardBackgroundColor());

        columnButtons = new JButton[cols];
        for (int i = 0; i < cols; i++) {
            columnButtons[i] = new JButton(ViewConstants.DROP_ARROW);
            columnButtons[i].setFont(new Font(ViewConstants.FONT_FAMILY, Font.BOLD, ViewConstants.FONT_SIZE_MEDIUM));
            columnButtons[i].setBackground(colorScheme.getButtonBackgroundColor());
            columnButtons[i].setForeground(colorScheme.getTextColor());
            columnButtons[i].setFocusPainted(false);
            columnButtons[i].setBorder(BorderFactory.createLineBorder(colorScheme.getButtonBorderColor(), 2));
            dropPanel.add(columnButtons[i]);
        }
        return dropPanel;
    }

    private JPanel buildBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        boardPanel.setBackground(colorScheme.getBoardBackgroundColor());
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        boardCells = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardCells[i][j] = new JButton();
                boardCells[i][j].setOpaque(true);
                boardCells[i][j].setBackground(colorScheme.getEmptyCellColor());
                boardCells[i][j].setBorder(BorderFactory.createLineBorder(colorScheme.getBorderColor(), 2));
                boardCells[i][j].setFocusPainted(false);
                boardPanel.add(boardCells[i][j]);
            }
        }
        return boardPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font(ViewConstants.FONT_FAMILY, Font.BOLD, ViewConstants.FONT_SIZE_SMALL));
        button.setBackground(colorScheme.getControlButtonColor());
        button.setForeground(colorScheme.getTextColor());
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(colorScheme.getTextColor(), 1));
        button.setPreferredSize(new Dimension(ViewConstants.BUTTON_WIDTH, ViewConstants.BUTTON_HEIGHT));
        return button;
    }

    // Registro de listeners (el Controlador se suscribe)

    public void setGameListener(ActionListener listener) {
        for (int i = 0; i < cols; i++) {
            columnButtons[i].setActionCommand(String.valueOf(i));
            columnButtons[i].addActionListener(listener);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardCells[i][j].setActionCommand(String.valueOf(j));
                boardCells[i][j].addActionListener(listener);
            }
        }

        newGameButton.setActionCommand(ViewConstants.ACTION_NEW_GAME);
        newGameButton.addActionListener(listener);

        exitButton.setActionCommand(ViewConstants.ACTION_EXIT);
        exitButton.addActionListener(listener);
    }

    // Metodos que el Controlador invoca para actualizar la Vista

    public void drawBoard(int[][] logicalBoard) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int playerId = logicalBoard[i][j];
                boardCells[i][j].setBackground(colorScheme.getPlayerColor(playerId));
            }
        }
    }

    public void updateTurn(String playerName) {
        turnLabel.setText(String.format(ViewConstants.TURN_LABEL_FORMAT, playerName));
    }

    public void showWinnerMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Fin del Juego!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showDrawMessage() {
        JOptionPane.showMessageDialog(this, ViewConstants.DRAW_MESSAGE, "Fin del Juego!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int askNewGame() {
        return JOptionPane.showConfirmDialog(
                this,
                ViewConstants.NEW_GAME_PROMPT,
                "Fin del Juego",
                JOptionPane.YES_NO_OPTION
        );
    }
}
