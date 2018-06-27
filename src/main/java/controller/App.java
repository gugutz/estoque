package controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class App extends Application implements Initializable {


    private Stage stage;

    @FXML
    private BorderPane rootPane, contentPane;
    @FXML
    private Button linkMain, linkAddItem, linkRemoveItem, linkItemDetails, buttonQuit;
    @FXML
    private Label bottomInfoLabel;

    Router router;

    public App() {
        router = new Router(contentPane);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        // make the stage
        stage = primaryStage;

        // set the scene
        Parent rootElement = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        int mainSceneWidth = 1000;
        int mainSceneHeight = 720;
        Scene mainScene = new Scene(rootElement, mainSceneWidth, mainSceneHeight);
        mainScene.getStylesheets().add("css/app.css");

        // set the stage
        stage.setScene(mainScene);
        String stageTitle = "Sistema de Estoque v0.1";
        stage.setTitle(stageTitle);
        stage.sizeToScene();
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    // ******************************************************************************
    // SCREEN HANDLING
    // ******************************************************************************

//
    public static void changeScreen(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        Parent mainScreenParent = FXMLLoader.load(App.class.getResource(contentPath));
        Scene newScene = new Scene(mainScreenParent, 800, 400);
        newScene.getStylesheets().add("css/app.css");
        // sets the scene to the main stage
    }

    public FXMLLoader getContentNode(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(contentPath));
//        contentPane.getChildren().clear();
//        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
        this.contentPane.setLayoutX(500);
        this.contentPane.setLayoutY(500);
        return loader;
    }


    public BorderPane getContentPane() {
        return contentPane;
    }

    public Router getRouter() {
        return this.router;
    }
//     ******************************************************************************


    // ******************************************************************************
    // EVENT LISTENERS
    // ******************************************************************************
    public void buttonListItemsClicked(ActionEvent actionEvent) throws IOException {
         BorderPane newContent =  router.setContentPane(contentPane, "ListItems");

    }

    public void buttonListItems2Clicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "ListItems2");
    }

    public void linkAddItemClicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "AddItem");
    }


    public void linkItemDetailsClicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "ItemDetails");
    }


    public void closeApplication() {
        Platform.exit();
        System.exit(0);
    }
    // ******************************************************************************


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // faz a tela inicial ser a tabela de itens do estoque
        try {
            router.setContentPane(contentPane, "AddItem");
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonQuit.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER) {
                closeApplication();
            }
        });
    }
}

