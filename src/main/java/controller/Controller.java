package controller;

import model.GameModel;
import view.ViewConstants;
import view.ViewLogin;
import view.ViewBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador: mediador puro entre Modelo y Vista.
 * Importa de model/ para logica de negocio.
 * Importa de view/ para coordinar la interfaz.
 * No contiene logica de negocio ni codigo de renderizado.
 */
public class Controller implements ActionListener {

    private final GameModel gameModel;
    private final ViewLogin loginView;
    private final ViewBoard boardView;

    public Controller(GameModel gameModel, ViewLogin loginView, ViewBoard boardView) {
        this.gameModel = gameModel;
        this.loginView = loginView;
        this.boardView = boardView;

        loginView.setLoginListener(this);
        boardView.setGameListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case ViewConstants.ACTION_LOGIN    -> handleLogin();
            case ViewConstants.ACTION_NEW_GAME -> handleNewGame();
            case ViewConstants.ACTION_EXIT     -> handleExit();
            default                            -> handleColumnClick(command);
        }
    }

    private void handleLogin() {
        try {
            String nameOne = loginView.getPlayerOneName();
            String nameTwo = loginView.getPlayerTwoName();

            gameModel.createPlayers(nameOne, nameTwo);

            loginView.setVisible(false);
            boardView.setVisible(true);
            refreshBoard();
        } catch (IllegalArgumentException exception) {
            loginView.showErrorMessage(exception.getMessage());
        }
    }

    private void handleNewGame() {
        gameModel.resetGame();
        refreshBoard();
    }

    private void handleExit() {
        boardView.dispose();
        loginView.dispose();
    }

    private void handleColumnClick(String command) {
        try {
            int column = Integer.parseInt(command);

            if (!gameModel.tryPlayMove(column)) {
                boardView.showErrorMessage(ViewConstants.COLUMN_FULL_MESSAGE);
                return;
            }

            gameModel.processGameState();

            if (gameModel.isGameOver()) {
                handleGameOver();
                return;
            }

            refreshBoard();
        } catch (NumberFormatException ignored) {
            // Ignora eventos que no sean coordenadas numericas
        }
    }

    private void handleGameOver() {
        refreshBoard();

        if (gameModel.isDraw()) {
            boardView.showDrawMessage();
        } else {
            String winnerMessage = String.format(
                    ViewConstants.WINNER_MESSAGE_FORMAT,
                    gameModel.getWinnerName()
            );
            boardView.showWinnerMessage(winnerMessage);
        }

        if (boardView.askNewGame() == 0) {
            handleNewGame();
        } else {
            handleExit();
        }
    }

    private void refreshBoard() {
        boardView.drawBoard(gameModel.getBoard());
        boardView.updateTurn(gameModel.getCurrentPlayerName());
    }
}
