package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    // stage = window. think of stage as the app window
    Stage stage;
    String stageTitle;

    // the scene is the content of the window
    Scene mainScene;
    int mainSceneWidth, getMainSceneHeight;

    // the parent is the fxml main.resources.fxml that is loaded in the scene of the stage
    Parent root;

    @Override
    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main/resources/fxml/Main.fxml"));
        mainSceneWidth = 1000;
        getMainSceneHeight = 800;
        stageTitle = "Sistema de Estoque v0.1";
        stage = primaryStage;
        stage.setTitle(stageTitle);

        mainScene = new Scene(root, mainSceneWidth, getMainSceneHeight);

        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
