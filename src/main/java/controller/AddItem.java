package controller;

import db.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TableItem;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddItem implements Initializable {

    @FXML
    public TextField productCode, productLine, productDescription, field_qtde, productWeight;
    @FXML
    private TableView<TableItem> products_table;
    @FXML
    private TableColumn col_id;
    @FXML
    private TableColumn col_codigo;
    @FXML
    private TableColumn col_qtde;


    private ObservableList<TableItem> row = FXCollections.observableArrayList();

    public void addItemToDatabase(ActionEvent actionEvent) {
        try {
            String code = productCode.getText();
            String line = productLine.getText();
            String description = productDescription.getText();
            Integer amount = Integer.valueOf(field_qtde.getText());
            Double weight = Double.parseDouble(productWeight.getText());

            // connect to db
            Connection connection = DB.connect();

            //criando comando para passar os dados do Jtextfild (usuário)
            // SQL insert query
            String QUERY = "INSERT INTO perfis(rowid, codigo, descricao, linha, qtde, peso) VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(QUERY);

            //passando o texto do campo do usuário para a String de inserção "query";
            statement.setString(1, null);
            statement.setString(2, code);
            statement.setString(3, description);
            statement.setString(4, line);
            statement.setInt(5, amount);
            statement.setDouble(6, weight);

            //executando a inserção no DB
            statement.executeUpdate();


            //encerrando a conexão
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ResultSet results = null;

        try {

            String selectQuery = "SELECT rowid, codigo, qtde from perfis;";
            results = DB.select(selectQuery);

            while (results.next()) {
                Integer id = results.getInt("rowid");
                String codigo = results.getString("codigo");
                Integer qtde = results.getInt("qtde");

                // salvando o objeto que vai ser colocado nas linhas da tabela
                row.add(new TableItem(id, codigo, qtde));

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_qtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));


        //FINALLY ADDED TO TableView
        products_table.setItems(row);
    }
}



