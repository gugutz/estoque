
import controller.Router;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class App extends Application implements Initializable {

    private Stage stage;
    private String stageTitle;
    private Scene mainScene;
    private int mainSceneWidth, mainSceneHeight;

    Parent rootElement, landingPage;

    @FXML
    public BorderPane rootPane, contentPane;

    @FXML
    public Button linkMain, linkAddItem, linkRemoveItem, linkItemDetails;
    @FXML
    public Label bottomInfoLabel;

    @Override
    public void start(Stage primaryStage) throws IOException {
        rootElement = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        mainSceneWidth = 940;
        mainSceneHeight = 820;
        stageTitle = "Sistema de Estoque v0.1";
        stage = primaryStage;
        stage.setTitle(stageTitle);

        mainScene = new Scene(rootElement, mainSceneWidth, mainSceneHeight);
        mainScene.getStylesheets().add("css/app.css");
        stage.setScene(mainScene);
        stage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }


    // EVENT LISTENERS
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


    public void setContentPane(String path) throws IOException {
        String contentLocation = "/fxml/";
        String contentPath = (contentLocation + path + ".fxml");
        contentPane.getChildren().clear();
//        contentPane.setCenter(FXMLLoader.load(getClass().getResource(contentPath)));
        contentPane.getChildren().add(FXMLLoader.load(getClass().getResource(contentPath)));
//        contentPane.setLayoutX(0);
//        contentPane.setLayoutY(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // faz a tela inicial ser a tabela de itens do estoque
        try {
            setContentPane("ListItems2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
