package controller;

import database.DB;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddItem {

    @FXML
    public TextField productCode, productLine, productDescription, productWeight;

    Connection connection = null;
    PreparedStatement statement;
    String code;
    String description;
    Double weight;

    private static final String QUERY = "INSERT INTO perfis VALUES(?, ?, ?, ?)";

    public void saveItem() {
        try {
            this.code = productCode.getText();
            this.description = productDescription.getText();
            this.weight = Double.parseDouble(productWeight.getText());

            // SQL insert query


            this.connection = DB.connect();

            //criando comando para passar os dados do Jtextfild (usuário)
            this.statement = connection.prepareStatement(QUERY);

            //passando o texto do campo do usuário para a String de inserção "query";
            statement.setString(1, this.code);

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
