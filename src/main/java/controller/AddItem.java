package controller;

import db.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.sql.*;

public class AddItem extends BorderPane {

   @FXML
    public TextField productCode, productLine, productDescription, productWeight;

    public void addItemToDatabase(ActionEvent actionEvent)  {
        try {
            String code = productCode.getText();
            String line = productLine.getText();
            String description = productDescription.getText();
            Double weight = Double.parseDouble(productWeight.getText());

            // connect to db
            Connection connection = DB.connect();

            //criando comando para passar os dados do Jtextfild (usuário)
            // SQL insert query
            String QUERY = "INSERT INTO perfis(rowid, codigo, descricao, linha, peso) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(QUERY);

            //passando o texto do campo do usuário para a String de inserção "query";
            statement.setString(1, null);
            statement.setString(2, code);
            statement.setString(3, description);
            statement.setString(4, line);
            statement.setDouble(5, weight);

            //executando a inserção no DB
            statement.executeUpdate();


            //encerrando a conexão
            statement.close();
            connection.close();


            } catch (SQLException e) {
                System.out.println("Ocorreu um erro com o SQL");
                e.printStackTrace();
            }
    }

}
