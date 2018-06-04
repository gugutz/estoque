package main_screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class MainScreen {

    @FXML
    Label bottomInfoLabel;

    public void changeTextButtonClicked(ActionEvent actionEvent) {
        bottomInfoLabel.setText("Teste");
    }

    public void loginButtonClicked(javafx.event.ActionEvent actionEvent) throws IOException {
        // goes to the main screen after logging in
        Parent loginScreenParent = FXMLLoader.load(getClass().getResource("../login/LoginScreen.fxml"));
        Scene loginScreenScene = new Scene(loginScreenParent, 800, 400);

        //This line gets the Stage information
        // ie: get the main window
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        // sets the scene to the main screen
        window.setScene(loginScreenScene);
        window.show();
    }
}
