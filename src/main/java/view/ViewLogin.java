package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista de login. Solo muestra campos y recibe input.
 * No importa nada del modelo.
 */
public class ViewLogin extends JFrame {

    private final ColorScheme colorScheme;
    private JTextField playerOneNameField;
    private JTextField playerTwoNameField;
    private JButton loginButton;

    public ViewLogin() {
        this(new ColorScheme());
    }

    public ViewLogin(ColorScheme colorScheme) {
        super(ViewConstants.LOGIN_TITLE);
        this.colorScheme = colorScheme;
        initializeComponents();
        configureWindow();
    }

    private void configureWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ViewConstants.LOGIN_WINDOW_WIDTH, ViewConstants.LOGIN_WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(colorScheme.getLoginBackgroundColor());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titulo
        JLabel titleLabel = new JLabel(ViewConstants.LOGIN_SUBTITLE, SwingConstants.CENTER);
        titleLabel.setFont(new Font(ViewConstants.FONT_FAMILY, Font.BOLD, ViewConstants.FONT_SIZE_LARGE));
        titleLabel.setForeground(colorScheme.getTextColor());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Jugador 1
        JLabel playerOneLabel = new JLabel(ViewConstants.PLAYER_1_LABEL);
        playerOneLabel.setForeground(colorScheme.getCyanColor());
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(playerOneLabel, gbc);

        playerOneNameField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(playerOneNameField, gbc);

        // Jugador 2
        JLabel playerTwoLabel = new JLabel(ViewConstants.PLAYER_2_LABEL);
        playerTwoLabel.setForeground(colorScheme.getOrangeColor());
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(playerTwoLabel, gbc);

        playerTwoNameField = new JTextField();
        gbc.gridx = 1;
        mainPanel.add(playerTwoNameField, gbc);

        // Boton de login
        loginButton = new JButton(ViewConstants.LOGIN_BUTTON_TEXT);
        loginButton.setBackground(colorScheme.getControlButtonColor());
        loginButton.setForeground(colorScheme.getTextColor());
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        add(mainPanel);
    }

    // Getters para que el Controlador lea el input

    public String getPlayerOneName() {
        return playerOneNameField.getText();
    }

    public String getPlayerTwoName() {
        return playerTwoNameField.getText();
    }

    // Registro de listener

    public void setLoginListener(ActionListener listener) {
        loginButton.setActionCommand(ViewConstants.ACTION_LOGIN);
        loginButton.addActionListener(listener);
    }

    // Metodo para mostrar errores (invocado por el Controlador)

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
