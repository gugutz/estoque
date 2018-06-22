package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Router {

    public Router() {


    }



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

    public void setRootPane(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
//        rootPane.getChildren().clear();
//        rootPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
//        rootPane.setLayoutX(0);
//        rootPane.setLayoutY(0);
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

   public void newScreen(ItemDetails path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource(contentPath));
        Scene mainScreenScene = new Scene(mainScreenParent, 800, 400);
        // sets the scene to the main stage
        Stage stage = new Stage();
        stage.setScene(mainScreenScene);
        stage.show();
    }
}

