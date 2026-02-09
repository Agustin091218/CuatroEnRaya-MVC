package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.ColorScheme;
import model.GameConstants;

/**
 * ViewBoard es la vista principal del juego.
 * Muestra el tablero, los botones de control y el estado del juego.
 */
public class ViewBoard extends JFrame {

    private JButton[] columnButtons;    // Botones de flecha arriba
    private JButton[][] boardCells;     // La grilla del tablero
    private JLabel turnLabel;
    private JButton newGameButton;
    private JButton exitButton;

    // Esquema de colores inmutable
    private final ColorScheme colorScheme;

    public ViewBoard() {
        this(new ColorScheme());
    }

    // Constructor con esquema de colores personalizado
    public ViewBoard(ColorScheme colorScheme) {
        super(GameConstants.GAME_TITLE); 
        this.colorScheme = colorScheme;

        setSize(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        //panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorScheme.getButtonBackgroundColor());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta de Turno
        turnLabel = new JLabel();
        turnLabel.setFont(new Font(GameConstants.FONT_FAMILY, Font.BOLD, GameConstants.FONT_SIZE_LARGE));
        turnLabel.setForeground(colorScheme.getTextColor());
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(turnLabel, BorderLayout.CENTER);

        // Panel de Botones de Control (Nueva Partida / Salir)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        controlPanel.setBackground(colorScheme.getButtonBackgroundColor());

        newGameButton = createButton(GameConstants.NEW_GAME_BUTTON_TEXT);
        exitButton = createButton(GameConstants.EXIT_BUTTON_TEXT);

        controlPanel.add(newGameButton);
        controlPanel.add(exitButton);
        topPanel.add(controlPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Panel del tablero
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(colorScheme.getBoardBackgroundColor());
        centerContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de Botones de Columna 
        JPanel dropButtonPanel = new JPanel(new GridLayout(1, GameConstants.BOARD_COLS, 5, 0));
        dropButtonPanel.setBackground(colorScheme.getBoardBackgroundColor());

        columnButtons = new JButton[GameConstants.BOARD_COLS];

        for (int i = 0; i < GameConstants.BOARD_COLS; i++) {
            columnButtons[i] = new JButton(GameConstants.DROP_ARROW); // Flecha desde constantes
            columnButtons[i].setFont(new Font(GameConstants.FONT_FAMILY, Font.BOLD, GameConstants.FONT_SIZE_MEDIUM));
            columnButtons[i].setBackground(colorScheme.getButtonBackgroundColor());
            columnButtons[i].setForeground(colorScheme.getTextColor());
            columnButtons[i].setFocusPainted(false);
            columnButtons[i].setBorder(BorderFactory.createLineBorder(colorScheme.getButtonBorderColor(), 2));
            dropButtonPanel.add(columnButtons[i]);
        }
        centerContainer.add(dropButtonPanel, BorderLayout.NORTH);

        // Grilla del Tablero
        JPanel boardPanel = new JPanel(new GridLayout(GameConstants.BOARD_ROWS, GameConstants.BOARD_COLS, 5, 5));
        boardPanel.setBackground(colorScheme.getBoardBackgroundColor());
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        boardCells = new JButton[GameConstants.BOARD_ROWS][GameConstants.BOARD_COLS];

        for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {
            for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                boardCells[i][j] = new JButton();
                boardCells[i][j].setOpaque(true);
                boardCells[i][j].setBackground(colorScheme.getEmptyCellColor());
                boardCells[i][j].setBorder(BorderFactory.createLineBorder(colorScheme.getBorderColor(), 2));
                boardCells[i][j].setFocusPainted(false);
                boardPanel.add(boardCells[i][j]);
            }
        }
        centerContainer.add(boardPanel, BorderLayout.CENTER);
        add(centerContainer, BorderLayout.CENTER);
    }

    // Método auxiliar para crear botones estandarizados
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font(GameConstants.FONT_FAMILY, Font.BOLD, GameConstants.FONT_SIZE_SMALL));
        btn.setBackground(colorScheme.getControlButtonColor());
        btn.setForeground(colorScheme.getTextColor());
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(colorScheme.getTextColor(), 1));
        btn.setPreferredSize(new Dimension(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT));
        return btn;
    }

    /**
     * Asigna el controlador a todos los componentes interactivos.
     */
    public void setGameListener(ActionListener listener) {
        //Listeners para las flechas superiores
        for (int i = 0; i < GameConstants.BOARD_COLS; i++) {
            columnButtons[i].setActionCommand(String.valueOf(i));
            columnButtons[i].addActionListener(listener);
        }

        //Listeners para las celdas del tablero
        for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {
            for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                boardCells[i][j].setActionCommand(String.valueOf(j)); // Manda el número de columna
                boardCells[i][j].addActionListener(listener);
            }
        }

        //Listeners para botones de control
        newGameButton.setActionCommand(GameConstants.ACTION_NEW_GAME);
        newGameButton.addActionListener(listener);

        exitButton.setActionCommand(GameConstants.ACTION_EXIT);
        exitButton.addActionListener(listener);
    }

    /**
     * Actualiza los colores del tablero
     */
    public void drawBoard(int[][] logicalBoard) {
        for (int i = 0; i < GameConstants.BOARD_ROWS; i++) {
            for (int j = 0; j < GameConstants.BOARD_COLS; j++) {
                int playerId = logicalBoard[i][j];
                // El ColorScheme decide qué color corresponde a cada ID
                boardCells[i][j].setBackground(colorScheme.getPlayerColor(playerId));
            }
        }
    }

    /**
     * Actualiza el texto del turno
     */
    public void updateTurn(String playerName) {
        turnLabel.setText(String.format(GameConstants.TURN_LABEL_FORMAT, playerName));
    }

    //Metodos para mostrar mensajes

    public void showWinnerMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "¡Fin del Juego!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int askNewGame() {
        return JOptionPane.showConfirmDialog(
            this,
            GameConstants.NEW_GAME_PROMPT,
            "Fin del Juego",
            JOptionPane.YES_NO_OPTION
        );
    }
}
