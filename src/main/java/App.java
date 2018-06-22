
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

    @FXML
    public BorderPane rootPane, contentPane;
    @FXML
    public Button linkMain, linkAddItem, linkRemoveItem, linkItemDetails;
    @FXML
    public Label bottomInfoLabel;

    public Router router = new Router();


    @Override
    public void start(Stage primaryStage) throws IOException {

        String stageTitle = "Sistema de Estoque v0.1";
        stage = primaryStage;
        stage.setTitle(stageTitle);

        Scene mainScene = getMainScreen();
        mainScene.getStylesheets().add("css/app.css");

        stage.setScene(mainScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Scene getMainScreen() throws IOException {
        Scene mainScene;
        Parent rootElement = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        int mainSceneWidth = 940;
        int mainSceneHeight = 820;
        mainScene = new Scene(rootElement, mainSceneWidth, mainSceneHeight);
        mainScene.getStylesheets().add("css/app.css");
        return mainScene;
    }

    // EVENT LISTENERS
    public void linkMainClicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "ListItems2");
    }

    public void linkAddItemClicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "AddItem");
    }

    public void linkRemoveItemClicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "RemoveItem");
    }

    public void linkItemDetailsClicked(ActionEvent actionEvent) throws IOException {
        router.setContentPane(contentPane, "ItemDetails");
    }

    public void closeApplication(ActionEvent e) {
        Platform.exit();
        System.exit(0);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // faz a tela inicial ser a tabela de itens do estoque
        try {
            Router router = new Router();
            router.setContentPane(contentPane, "ListItems2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
