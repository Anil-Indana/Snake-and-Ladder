package com.example.snake_and_ladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Group root;
    @Override
    public void start(Stage primaryStage) throws IOException {
        root = new Group();
        GamePage page = new GamePage();
        root.getChildren().add(page.root);
        primaryStage.setTitle("Snake and Ladder");
        primaryStage.setScene(new Scene(root,800,500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}