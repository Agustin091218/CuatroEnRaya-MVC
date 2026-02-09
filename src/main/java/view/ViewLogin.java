package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.GameConstants;
public class ViewLogin extends JFrame {
    private JTextField txtName1;
    private JTextField txtName2;
    private JButton btnEnter;

    public ViewLogin() {
        super(GameConstants.LOGIN_TITLE);
        initializeComponents();
        configureWindow();
    }

    private void configureWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GameConstants.LOGIN_WINDOW_WIDTH, GameConstants.LOGIN_WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initializeComponents() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(60, 63, 65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel(GameConstants.LOGIN_SUBTITLE, SwingConstants.CENTER);
        lblTitulo.setFont(new Font(GameConstants.FONT_FAMILY, Font.BOLD, GameConstants.FONT_SIZE_LARGE));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelPrincipal.add(lblTitulo, gbc);

        JLabel lblJugador1 = new JLabel(GameConstants.PLAYER_1_LABEL);
        lblJugador1.setForeground(Color.CYAN);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelPrincipal.add(lblJugador1, gbc);

        txtName1 = new JTextField();
        gbc.gridx = 1;
        panelPrincipal.add(txtName1, gbc);

        JLabel lblJugador2 = new JLabel(GameConstants.PLAYER_2_LABEL);
        lblJugador2.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(lblJugador2, gbc);

        txtName2 = new JTextField();
        gbc.gridx = 1;
        panelPrincipal.add(txtName2, gbc);

        btnEnter = new JButton(GameConstants.LOGIN_BUTTON_TEXT);
        btnEnter.setBackground(new Color(75, 110, 175));
        btnEnter.setForeground(Color.WHITE);
        btnEnter.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(btnEnter, gbc);

        add(panelPrincipal);
    }

    public String getName1() {
        return txtName1.getText();
    }

    public String getName2() {
        return txtName2.getText();
    }

    public void setLoginListener(ActionListener listener) {
        btnEnter.setActionCommand(GameConstants.ACTION_LOGIN);
        btnEnter.addActionListener(listener);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
