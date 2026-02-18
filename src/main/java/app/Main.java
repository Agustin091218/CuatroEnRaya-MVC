package app;

import model.GameModel;
import controller.Controller;
import view.ViewLogin;
import view.ViewBoard;

/**
 * Punto de entrada. Composition Root: crea las tres capas y las cablea.
 * Es el unico lugar donde se conocen todas las dependencias.
 */
public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameModel gameModel = new GameModel();

            ViewLogin loginView = new ViewLogin();
            ViewBoard boardView = new ViewBoard(
                    gameModel.getBoardRows(),
                    gameModel.getBoardCols()
            );

            new Controller(gameModel, loginView, boardView);

            loginView.setVisible(true);
        });
    }
}
