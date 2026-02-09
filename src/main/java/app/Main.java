package app;

import model.GameModel;
import controller.Controller;
import view.ViewLogin;
import view.ViewBoard;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameModel gameModel = new GameModel();
            ViewLogin loginView = new ViewLogin();
            ViewBoard boardView = new ViewBoard();

            new Controller(gameModel, loginView, boardView);

            loginView.setVisible(true);
        });
    }
}
