package com.example.tictactoe;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class MyButton extends Button {

    private final int row;
    private final int col;
    private double x;
    private double y;
    private Bounds boundsInScene;
    MyButton(int row, int col) {
        this.row = row;
        this.col = col;
        setMaxWidth(1000);
        setMaxHeight(1000);
        setPadding(new Insets(10));
        setStyle("-fx-font-size: 25;");
        setText(" ");
    }

     public void setCoordinates() {
        boundsInScene = super.localToScene(super.getBoundsInParent());
        x = boundsInScene.getMinX();
        y = boundsInScene.getMaxY();
     }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
