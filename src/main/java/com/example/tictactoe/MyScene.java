    package com.example.tictactoe;

    import javafx.beans.property.SimpleIntegerProperty;
    import javafx.event.Event;
    import javafx.event.EventHandler;
    import javafx.geometry.Insets;
    import javafx.scene.Node;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.layout.*;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Line;


    public class MyScene extends VBox implements EventHandler {

        private HBox[] row = new HBox[3];
        private HBox[] scoreBoard = new HBox[2];
        private int[] scores = new int[2];
        private HBox newGameHbox = new HBox();
        private Line winnerLine;
        private StackPane stackPane;
        private SimpleIntegerProperty[] property = new SimpleIntegerProperty[2];
        private int counter=0;
        protected static MyButton[][] buttons = new MyButton[3][3];
        protected static int[][] game = {{3,4,5},{6,7,8},{9,10,11}};
        private static int winner; // x - 1, o - 0
        MyScene(StackPane stackPane) {
            this.stackPane = stackPane;
            this.stackPane.getChildren().add(this);

            for (int i = 0 ; i<3 ; i++) {
                row[i] = new HBox();
                row[i].setSpacing(5);
            }
            scores[0] = 0;
            scores[1] = 0;
            BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
            Background background = new Background(backgroundFill);
            setBackground(background);

            setPrefSize(500,500);
            setPadding(new Insets(10));
            VBox.setVgrow(row[0],Priority.ALWAYS);
            VBox.setVgrow(row[1],Priority.ALWAYS);
            VBox.setVgrow(row[2],Priority.ALWAYS);
            setSpacing(5);
            createBt();
//            winnerLine = new Line();
//            winnerLine.setOpacity(0);
//            this.stackPane.getChildren().add(winnerLine);
            //diziyi verdik direkt
            super.getChildren().addAll(row);
            DisplayScoreBoard();
            super.getChildren().addAll(scoreBoard);
            newGame();
            super.getChildren().add(newGameHbox);
        }

        public void createBt() {
            for(int i = 0 ; i < 3 ; i++) {
                for(int j = 0 ; j < 3 ; j++) {
                    buttons[i][j] = new MyButton(i,j);
                    buttons[i][j].setOnAction(this);
                    add(i, buttons[i][j]);
                    buttons[i][j].setCoordinates();
                }
            }
        }
        private void add(int rowIndex, Node node) {
            HBox.setHgrow(node, Priority.ALWAYS);
            row[rowIndex].getChildren().add(node);
        }

        protected void newGame() {
            Button newGameButton = new Button();
            newGameButton.setPrefHeight(150);
            newGameButton.setPrefWidth(800);
            newGameButton.setPadding(new Insets(10));
            newGameButton.setStyle("-fx-font-size: 25;");
            newGameButton.setText("NEW GAME");

            newGameHbox.getChildren().add(newGameButton);

            newGameButton.setOnAction(e-> {
                for (int i = 0; i < game.length; i++) {
                    for (int j = 0; j < game[i].length; j++) {
                        game[i][j] = (game[i][j] + 2) * 2;
                    }
                }

                for(MyButton btn[] : buttons) {
                    for(MyButton bt : btn) {
                        bt.setDisable(false);
                        bt.setText(" ");
                    }
                }
                counter = 0;
//                winnerLine.setOpacity(0);
            });
        }
        private void DisplayScoreBoard() {

            for(int i = 0 ; i < 2 ; i++) {
                scoreBoard[i] = new HBox();
                property[i] = new SimpleIntegerProperty();
            }

            Label xLabel = new Label("X Score:");
            Label oLabel = new Label("O Score:");
            xLabel.setPadding(new Insets(30,50,20,20));
            oLabel.setPadding(new Insets(20,50,0,20));

            xLabel.setStyle("-fx-font-size: 25;");
            oLabel.setStyle("-fx-font-size: 25;");


            Label xScore = new Label();
            Label oScore = new Label();
            xScore.setPadding(new Insets(30,50,20,20));
            oScore.setPadding(new Insets(20,50,0,20));
            xScore.setStyle("-fx-font-size: 25;");
            oScore.setStyle("-fx-font-size: 25;");
            xScore.textProperty().bind(property[1].asString());
            oScore.textProperty().bind(property[0].asString());

            scoreBoard[0].getChildren().addAll(oLabel, oScore);
            scoreBoard[1].getChildren().addAll(xLabel, xScore);
        }
//        public void drawLine(Line line, MyButton myButton, MyButton myButton2) {
//            line.setStroke(Color.BLACK);
//            line.setStrokeWidth(15);
//
//            line.setStartX(myButton.getX());
//            line.setStartY(myButton.getY());
//
//            line.setEndX(myButton2.getY());
//            line.setEndY(myButton2.getX());
//
//            line.setOpacity(1);
//        }

        public void checkSituation(int value) {
            winner = -1;
            for (int i = 0; i < 3; i++) {
                // Check row
                if (game[i][0] == value && game[i][1] == value && game[i][2] == value) {
                    winner = value;
                }

                // Check column
                if (game[0][i] == value && game[1][i] == value && game[2][i] == value) {
                    winner = value;
                }
            }

            // Check diagonals
            if (game[0][0] == value && game[1][1] == value && game[2][2] == value) {
                winner = value;
            }

            if (game[0][2] == value && game[1][1] == value && game[2][0] == value) {
                winner = value;
            }
            if(winner == 1 || winner == 0) {
                int idx = (winner == 1 ? 1 : 0);
                property[idx].set(property[idx].get()+1);
                for(MyButton btn[] : buttons) {
                    for(MyButton bt : btn) {
                        bt.setDisable(true);
                    }
                }
            }

        }



        @Override
        public void handle(Event event) {
            MyButton mb = ((MyButton) event.getSource());
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
