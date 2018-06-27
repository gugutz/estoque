package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Router implements RouterFX{

    private Stage stage;
    private BorderPane contentPane;


    public Router() {
    }

    public Router(BorderPane contentPane) {
        this.contentPane = contentPane;
    }


    public Scene getMainScene() {
        Scene mainScene = (Scene) this.getMainScene();
        return mainScene;
    }


    @Override
    public Stage newStage() {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        return stage;
    }

    @Override
    public Scene newScene(Stage stage, String path) {
        return null;
    }

    @Override
    public Parent newParent(Scene scene, String path) {
        return null;
    }

    @Override
    public BorderPane setContentPane(BorderPane contentPane, String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        contentPane.getChildren().clear();
//        contentPane.setCenter(FXMLLoader.load(getClass().getResource(contentPath)));
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
//        contentPane.setLayoutX(0);
//        contentPane.setLayoutY(0);
        return contentPane;
    }

    @Override
    public Routes generateRoutes() {
        return null;
    }

    @Override
    public void resizeScene(int width, int height) {
        this.stage = (Stage) contentPane.getScene().getWindow();
        Parent currentParent = contentPane.getParent();
        Scene resizedScene = new Scene(currentParent, width, height);
        setStage(resizedScene);
    }

    public void setRootPane(String path) {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
//        rootPane.getChildren().clear();
//        rootPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
//        rootPane.setLayoutX(0);
//        rootPane.setLayoutY(0);
    }

    @Override
    public void setStage(Scene newScene) {
//        this.stage = (Stage) contentPane.getClass().getScene().getWindow();
    }


//    public Stage getCurrentStage () {
//        Stage stage = (Stage) rootPane.getScene().getWindow();
//        return stage;
//    }


//    public void changeScreen(String path) throws IOException {
//        String contentLocation = "/fxml/";
//        String contentPath = (contentLocation + path + ".fxml");
//        Parent mainScreenParent = FXMLLoader.load(getClass().getResource(contentPath));
//        Scene mainScreenScene = new Scene(mainScreenParent, 800, 400);
//        // sets the scene to the main stage
//        Stage stage = getCurrentStage();
//        stage.setScene(mainScreenScene);
//        stage.show();
//    }


    public void newWindow(int width, int height, String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource(contentPath));
        Scene mainScreenScene = new Scene(mainScreenParent, width, height);
        // sets the scene to the main stage
        Stage stage = new Stage();
        stage.setScene(mainScreenScene);
        stage.show();
        System.out.println("Sistema de Estoque v1");
    }
}

