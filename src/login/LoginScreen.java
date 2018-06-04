package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen {

    /**
     * Method to change between scenes
     */
    public void loginButtonClicked(ActionEvent event) throws IOException {

        // goes to the main screen after logging in
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("../main_screen/MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent, 800, 400);

        //This line gets the Stage information
        // ie: get the main window
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        // sets the scene to the main screen
        window.setScene(mainScreenScene);
        window.show();
    }

}
