package com.example.snake_and_ladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    @FXML
    ImageView player1,player2;
    @FXML
    Text number;
    @FXML
    Text changeTurn;
    int turn = 1;
    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeAndLadderCoOrd;
    @FXML
    public void roll(MouseEvent event){
        getSnakeAndLadCoOrd();
        Random random = new Random();
        int rolling = random.nextInt(6)+1;
        number.setText(""+rolling);
        if(turn == 1){
            Pair<Double,Double> moveCoOrd = movement(player1.getTranslateX(),player1.getTranslateY(),rolling);
            player1.setTranslateX(moveCoOrd.getKey());
            player1.setTranslateY(moveCoOrd.getValue());
            if(snakeAndLadderCoOrd.containsKey(new Pair<>(moveCoOrd.getKey(),moveCoOrd.getValue()))){
                player1.setTranslateX(snakeAndLadderCoOrd.get(new Pair<>(moveCoOrd.getKey(),moveCoOrd.getValue())).getKey());
                player1.setTranslateY(snakeAndLadderCoOrd.get(new Pair<>(moveCoOrd.getKey(),moveCoOrd.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());
        }else{
            Pair<Double,Double> moveCoOrd = movement(player2.getTranslateX(),player2.getTranslateY(),rolling);
            player2.setTranslateX(moveCoOrd.getKey());
            player2.setTranslateY(moveCoOrd.getValue());
            if(snakeAndLadderCoOrd.containsKey(new Pair<>(moveCoOrd.getKey(),moveCoOrd.getValue()))) {
                player2.setTranslateX(snakeAndLadderCoOrd.get(new Pair<>(moveCoOrd.getKey(), moveCoOrd.getValue())).getKey());
                player2.setTranslateY(snakeAndLadderCoOrd.get(new Pair<>(moveCoOrd.getKey(), moveCoOrd.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }
        if(rolling != 6){

            if(turn == 1){
                turn = 2;
                changeTurn.setText("Player 2's turn");
            }
            else{
                turn = 1;
                changeTurn.setText("Player 1's turn");
            }
        }
    }
    Pair<Double,Double> movement(double x,double y,int rolling){

        double MoveX = x;
        double MoveY = y;

        if(MoveY%100 == 0){
            MoveX += rolling*50;
            if(MoveX > 500){
                MoveX = 500 * 2 - MoveX + 50;
                MoveY -= 50;
            }
        }
        else{
            MoveX -= rolling*50;
            if(MoveX < 50){
                if(MoveY == -450){
                    return new Pair<>(x,y);
                }
                MoveX = -1*(MoveX-50);
                MoveY -= 50;
            }
        }
        return new Pair<>(MoveX,MoveY);
    }
    void checkWin(double x , double y){
        if(x == 50 && y == -450){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Result!");
            if(turn == 1){
                alert.setContentText("Player 1 has won");
            }else{
                alert.setContentText("Player 2 has won");
            }
            alert.showAndWait();
        }
    }
    void getSnakeAndLadCoOrd(){
        snakeAndLadderCoOrd = new HashMap<>();
        snakeAndLadderCoOrd.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeAndLadderCoOrd.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));
        snakeAndLadderCoOrd.put(new Pair<>(200.0,-50.0),new Pair<>(350.0,0.0));
        snakeAndLadderCoOrd.put(new Pair<>(450.0,0.0),new Pair<>(500.0,-150.0));
        snakeAndLadderCoOrd.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));
        snakeAndLadderCoOrd.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));
        snakeAndLadderCoOrd.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));
        snakeAndLadderCoOrd.put(new Pair<>(350.0,-250.0),new Pair<>(350.0,-150.0));
        snakeAndLadderCoOrd.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));
        snakeAndLadderCoOrd.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));
        snakeAndLadderCoOrd.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));
        snakeAndLadderCoOrd.put(new Pair<>(50.0,-350.0),new Pair<>(50.0,-450.0));
        snakeAndLadderCoOrd.put(new Pair<>(150.0,-450.0),new Pair<>(100.0,-350.0));
        snakeAndLadderCoOrd.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));
        snakeAndLadderCoOrd.put(new Pair<>(400.0,-450.0),new Pair<>(400.0,-350.0));

    }
}
