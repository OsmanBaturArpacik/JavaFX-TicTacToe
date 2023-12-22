package com.example.tictactoe;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class TicTacToePane extends Pane implements EventHandler {
    private int[] scores = new int[2]; // scores[0] = 0; scores[1] = 0;
    private Line winnerLine;
    private Button newGameButton;
    private SimpleIntegerProperty[] property = {new SimpleIntegerProperty(), new SimpleIntegerProperty()};
    private int counter=0;
    protected static TicTacToeButton[][] buttons = new TicTacToeButton[3][3];
    protected static int[][] game = {{3,4,5},{6,7,8},{9,10,11}};
    private static int winner; // x - 1, o - 0
    TicTacToePane() {
        createBt();

        DisplayScoreBoard();

        setNewGameButton();

        setWinnerLine();

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background background = new Background(backgroundFill);
        setBackground(background);
    }

    protected void createBt() {
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                buttons[i][j] = new TicTacToeButton(i,j);
                buttons[i][j].setOnAction(this::handle);
                getChildren().add(buttons[i][j]);
            }
        }
    }

    private void setNewGameButton() {
        newGameButton = new Button();
        newGameButton.setStyle("-fx-font-size: 25;");
        newGameButton.setText("NEW GAME");
        newGameButton.setPrefWidth(3*(TicTacToeButton.height+ TicTacToeButton.margin) - TicTacToeButton.margin);
        newGameButton.setPrefHeight(150);
        newGameButton.setLayoutX(TicTacToeButton.margin);
        newGameButton.setLayoutY(3*(TicTacToeButton.height+ TicTacToeButton.margin) + TicTacToeButton.margin);
        newGameButton.setOnAction(this::newGameButtonHandle);
        super.getChildren().add(newGameButton);
    }
    protected void newGameButtonHandle(ActionEvent e) {
            for (int i = 0; i < game.length; i++) {
                for (int j = 0; j < game[i].length; j++) {
                    game[i][j] = (game[i][j] + 2) * 2;
                }
            }
            for(TicTacToeButton btn[] : buttons) {
                for(TicTacToeButton bt : btn) {
                    bt.setDisable(false);
                    bt.setText(" ");
                }
            }
            counter = 0;
            winnerLine.setOpacity(0);
    }
    private void DisplayScoreBoard() {
        Label xLabel = new Label("X Score:");
        xLabel.setLayoutX(TicTacToeButton.margin);
        xLabel.setLayoutY(4*(TicTacToeButton.height+ TicTacToeButton.margin) + TicTacToeButton.margin);
        xLabel.setStyle("-fx-font-size: 25;");

        Label oLabel = new Label("O Score:");
        oLabel.setLayoutX(TicTacToeButton.margin);
        oLabel.setLayoutY(4*(TicTacToeButton.height+ TicTacToeButton.margin) + TicTacToeButton.margin*3);
        oLabel.setStyle("-fx-font-size: 25;");

        Label xScore = new Label();
        xScore.setLayoutX(TicTacToeButton.margin*5);
        xScore.setLayoutY(4*(TicTacToeButton.height+ TicTacToeButton.margin) + TicTacToeButton.margin);
        xScore.setStyle("-fx-font-size: 25;");

        Label oScore = new Label();
        oScore.setLayoutX(TicTacToeButton.margin*5);
        oScore.setLayoutY(4*(TicTacToeButton.height+ TicTacToeButton.margin) + TicTacToeButton.margin*3);
        oScore.setStyle("-fx-font-size: 25;");

        xScore.textProperty().bind(property[1].asString());
        oScore.textProperty().bind(property[0].asString());

        super.getChildren().addAll(xLabel, xScore);
        super.getChildren().addAll(oLabel, oScore);
    }
    public void setWinnerLine() {
        winnerLine = new Line();
        winnerLine.setStroke(Color.BLACK);
        winnerLine.setStrokeWidth(5);
        winnerLine.setOpacity(0);
        super.getChildren().add(winnerLine);
    }
    public void drawLine(TicTacToeButton myButton, TicTacToeButton myButton2) {

        winnerLine.setStartX(myButton.getLayoutX() + TicTacToeButton.width/2.0);
        winnerLine.setStartY(myButton.getLayoutY() + TicTacToeButton.height/2.0);

        winnerLine.setEndX(myButton2.getLayoutX() + TicTacToeButton.width/2.0);
        winnerLine.setEndY(myButton2.getLayoutY() + TicTacToeButton.height/2.0);

    }

    public void checkSituation(int value) {
        winner = -1;
        for (int i = 0; i < 3; i++) {
            // Check row
            if (game[i][0] == value && game[i][1] == value && game[i][2] == value) {
                winner = value;
                drawLine(buttons[i][0], buttons[i][2]);
            }

            // Check column
            if (game[0][i] == value && game[1][i] == value && game[2][i] == value) {
                winner = value;
                drawLine(buttons[0][i], buttons[2][i]);
            }
        }

        // Check diagonals
        if (game[0][0] == value && game[1][1] == value && game[2][2] == value) {
            winner = value;
            drawLine(buttons[0][0], buttons[2][2]);
        }

        if (game[0][2] == value && game[1][1] == value && game[2][0] == value) {
            winner = value;
            drawLine(buttons[0][2], buttons[2][0]);
        }
        if (winner == 1 || winner == 0) {
            int idx = (winner == 1 ? 1 : 0);
            winnerLine.setOpacity(1);
            property[idx].set(property[idx].get()+1);
            for(TicTacToeButton btn[] : buttons) {
                for(TicTacToeButton bt : btn) {
                    bt.setDisable(true);
                }
            }
        }
    }

    // Button handle
    @Override
    public void handle(Event event) {
        TicTacToeButton mb = ((TicTacToeButton) event.getSource());
        if(counter++ % 2 == 0) {
            game[mb.getRow()][mb.getCol()] = 1;
            mb.setText("X"); // 1
            checkSituation(1);
        }
        else {
            game[mb.getRow()][mb.getCol()] = 0;
            mb.setText("O"); // 0
            checkSituation(0);
        }
        mb.setDisable(true);
    }
}
