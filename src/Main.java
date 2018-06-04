import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // adding semantics to the main stage
    Stage window;
    Scene loginScene, mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent login = FXMLLoader.load(getClass().getResource("./login/LoginScreen.fxml"));
        loginScene = new Scene(login, 800, 400);

        window.setTitle("Hello World");
        window.setScene(loginScene);
        window.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
