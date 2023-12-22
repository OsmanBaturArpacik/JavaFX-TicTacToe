package com.example.tictactoe;

import javafx.scene.control.Button;

public class TicTacToeButton extends Button {
    public static final int width = 130;
    public static final int height = 130;
    public static final int margin = 30;
//    private boolean isVisible = true;
    private final int row;
    private final int col;
    TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
        setPrefWidth(width);
        setPrefHeight(height);
        setLayoutX(col*width + margin*(col+1));
        setLayoutY(row*height + margin*(row+1));
        setStyle("-fx-font-size: 25;");
        setText(" ");
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
