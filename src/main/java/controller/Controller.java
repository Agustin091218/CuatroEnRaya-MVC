package controller;

import model.GameModel;
import model.GameConstants;
import view.ViewLogin;
import view.ViewBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * El Controlador es el intermediario que gestiona el flujo del juego
 * Recibe eventos de la Vista, invoca acciones en el Modelo y actualiza la Vista.
 */
public class Controller implements ActionListener {

    // Referencias inmutables al modelo y las vistas
    private final GameModel gameModel;
    private final ViewLogin loginView;
    private final ViewBoard boardView;

    // Constructor
    public Controller(GameModel gameModel, ViewLogin loginView, ViewBoard boardView) {
        this.gameModel = gameModel;
        this.loginView = loginView;
        this.boardView = boardView;

        // El controlador escucha los eventos
        loginView.setLoginListener(this);
        boardView.setGameListener(this);
    }

    // Punto de manejo de eventos 
    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();

        switch (comand) {
            case GameConstants.ACTION_LOGIN    -> handleLogin();
            case GameConstants.ACTION_NEW_GAME -> handleNewGame();
            case GameConstants.ACTION_EXIT     -> handleExit();
            default                            -> tryPlayMove(comand);
        }
    }

    private void handleLogin() {
        try {
            //Pasar datos de la vista al modelo
            gameModel.createPlayers(loginView.getName1(), loginView.getName2());

            // Gestionar transición de pantallas
            loginView.setVisible(false);
            boardView.setVisible(true);

            //Inicializar el tablero 
            refresh();
        } catch (IllegalArgumentException e) {
            // Si el modelo rechaza los datos, notificar a la vista
            loginView.showErrorMessage(e.getMessage());
        }
    }

    private void handleNewGame() {
        gameModel.resetGame();
        refresh();
    }

    private void handleExit() {
        boardView.dispose();
        loginView.dispose();
    }

    private void tryPlayMove(String comand) {
        try {
            int column = Integer.parseInt(comand);

            // Validar movimiento con el modelo
            if (!gameModel.tryPlayMove(column)) {
                boardView.showErrorMessage(GameConstants.COLUMN_FULL_MESSAGE);
                return;
            }

            // verificar si alguien ganó
            gameModel.processGameState();

            // Verificar fin del juego
            if (gameModel.isGameOver()) {
                String msg = String.format(GameConstants.WINNER_MESSAGE_FORMAT, gameModel.getWinnerName());
                boardView.showWinnerMessage(msg);

                // Jugar de nuevo o Salir
                if (boardView.askNewGame() == 0) {
                    handleNewGame();
                } else {
                    handleExit();
                }
                return; 
            }
            refresh();

        } catch (NumberFormatException ignored) {
            // Ignora eventos que no sean coordenadas numéricas
        }
    }

    // Sincroniza la Vista con el estado actual del Modelo
    private void refresh() {
        boardView.drawBoard(gameModel.getBoard());
        boardView.updateTurn(gameModel.getCurrentPlayerName());
    }
}
