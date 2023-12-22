package com.example.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane myStackPane = new StackPane();

        MyScene myScene = new MyScene(myStackPane);

        Scene scene = new Scene(myStackPane, 800, 800);

        primaryStage.setTitle("XOX");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
