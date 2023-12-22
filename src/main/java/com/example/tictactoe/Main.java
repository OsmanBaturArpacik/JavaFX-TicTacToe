package com.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int width = 3*(TicTacToeButton.width+ TicTacToeButton.margin) + TicTacToeButton.margin;
    public static final int height = 800;
    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(new TicTacToePane(), width, height);

        primaryStage.setResizable(false);
        primaryStage.setTitle("XOX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
