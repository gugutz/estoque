package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class MainController {

    @FXML
    public Button linkMain, linkAddItem, linkRemoveItem;
    @FXML
    public Label bottomInfoLabel;
    @FXML
    public BorderPane contentPane;
    @FXML
    public AnchorPane rootPane;

    public void setContentPane(String path) throws IOException {
        String contentLocation = "/main/resources/fxml/";
        String contentPath = (contentLocation + path);
        contentPane.getChildren().clear();
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
        contentPane.setLayoutX(0);
        contentPane.setLayoutY(0);
    }

    public void linkMainClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("Landing.fxml");
    }

    public void linkAddItemClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("AddItem.fxml");
    }

    public void linkRemoveItemClicked(ActionEvent actionEvent) throws IOException {
        setContentPane("RemoveItem.fxml");
    }

}
