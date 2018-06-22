package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveItem extends BorderPane {

    @FXML
    TextField productId;
    public void remoteItem() {
        try {

            Connection connection = null;


            PreparedStatement stmt = connection.prepareStatement("DELETE FROM perfis WHERE nome=?");

            stmt.setString(1, productId.getText());

            stmt.executeUpdate();

            stmt.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Ocorreu um Erro de conexão com o SQL: " + e.getMessage());
        }
    }

}
