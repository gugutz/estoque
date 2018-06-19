package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Router {

    @FXML
    public Button linkMain, linkAddItem, linkRemoveItem, linkItemDetails;
    @FXML
    public Label bottomInfoLabel;
    @FXML
    public BorderPane rootPane, contentPane;

    public void setRootPane(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        rootPane.getChildren().clear();
        rootPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
        rootPane.setLayoutX(0);
        rootPane.setLayoutY(0);
    }


    public void setContentPane(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        contentPane.getChildren().clear();
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
        contentPane.setLayoutX(0);
        contentPane.setLayoutY(0);
    }


    public Stage getCurrentStage () {
        // gets the main stage (window)
        Stage stage = (Stage) rootPane.getScene().getWindow();
        return stage;
    }


    public void changeScreen(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource(contentPath));
        Scene mainScreenScene = new Scene(mainScreenParent, 800, 400);
        // sets the scene to the main stage
        Stage stage = getCurrentStage();
        stage.setScene(mainScreenScene);
        stage.show();
    }
   public void newScreen(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource(contentPath));
        Scene mainScreenScene = new Scene(mainScreenParent, 800, 400);
        // sets the scene to the main stage
        Stage stage = new Stage();
        stage.setScene(mainScreenScene);
        stage.show();
    }

    public void linkMainClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("ListItems2");
    }

    public void linkAddItemClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("AddItem");
    }

    public void linkRemoveItemClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("RemoveItem");
    }

    public void linkItemDetailsClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("ItemDetails");
    }

    public void closeApplication(ActionEvent e) {
            Platform.exit();
            System.exit(0);
    }

}

