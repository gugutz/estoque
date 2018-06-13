package main.java.controller;

import main.java.db.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddItem {

    @FXML
    public TextField productCode, productLine, productDescription, productWeight;

    private Connection connection = null;
    private PreparedStatement statement;
    private String code;
    private String description;
    private String line;
    private Double weight;

    // SQL insert query
    private final String QUERY = "INSERT INTO perfis(codigo, descricao, linha, peso) VALUES(?, ?, ?, ?)";

    public void addItemToDatabase(ActionEvent actionEvent)  {
        try {
            this.code = productCode.getText();
            this.line = productLine.getText();
            this.description = productDescription.getText();
            this.weight = Double.parseDouble(productWeight.getText());

            // connect to db
            this.connection = DB.connect();

            //criando comando para passar os dados do Jtextfild (usuário)
            this.statement = connection.prepareStatement(QUERY);

            //passando o texto do campo do usuário para a String de inserção "query";
            this.statement.setString(1, this.code);
            this.statement.setString(2, this.description);
            this.statement.setString(3, this.line);
            this.statement.setDouble(4, this.weight);

            //executando a inserção no DB
            this.statement.executeUpdate();

            //encerrando a conexão
            this.statement.close();
            this.connection.close();

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro com o SQL");
                e.printStackTrace();
            }
    }

}
