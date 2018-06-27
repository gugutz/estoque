package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public interface RouterFX {

    Stage newStage();

    Scene newScene(Stage stage, String path);

    Parent newParent(Scene scene, String path);

    BorderPane setContentPane(BorderPane borderPane, String path) throws IOException;

    Routes generateRoutes();

    Scene getMainScene();
    void resizeScene(int width, int height);

    void setRootPane(String path);

    void setStage(Scene newScene);
}
