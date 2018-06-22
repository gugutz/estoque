package controller;

import db.DB;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class ItemDetails extends BorderPane implements Initializable {

    @FXML
    public TextField field_id, field_codigo, field_descricao, field_linha, field_peso;
    @FXML
    public Button buttonSave, buttonDelete, botaoPesquisar, buttonClearForm;
    private int id;
    private String code;
    private String queryTerm;


    // Creating the radio buttons that will settle the type of search (id or name)
    @FXML
    RadioButton radioSearchById, radioSearchByCode;

    // a classe ToogleGroup agrupa os botoes de radio, e facilitar extrair o item selecionado
    final ToggleGroup searchType = new ToggleGroup();

    private String column;

    public ItemDetails() {
        this.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
    }

    public ItemDetails(int id) throws SQLException {
        this.id = id;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioSearchById.setToggleGroup(searchType);
        radioSearchById.setSelected(true);
        radioSearchById.setToggleGroup(searchType);
        radioSearchByCode.setToggleGroup(searchType);
    }


    public void onBotaoPesquisarClicked(ActionEvent e) {
        getItem();
    }



    public void onButtonClearFormClicked(ActionEvent e) {
        clearForm();
    }



    public void onButtonDeleteClicked(ActionEvent e) throws SQLException {
        DB.close();
        String id = field_id.getText();
        final String query = "DELETE FROM perfis WHERE rowid = " + id + ";";
        clearForm();
        DB.delete(query);
    }


    public void onButtonEditClicked(ActionEvent e) throws SQLException {
        DB.close();
        final String QUERY = "UPDATE perfis SET codigo = ?, descricao = ?, linha = ?, peso = ? WHERE rowid = ?";
        Connection connection = DB.connect();
        PreparedStatement statement = connection.prepareStatement(QUERY);

        statement.setString(1, field_codigo.getText());
        statement.setString(2, field_descricao.getText());
        statement.setString(3, field_linha.getText());
        statement.setDouble(4, Double.parseDouble(field_peso.getText()));
        statement.setInt(5, Integer.parseInt(field_id.getText()));

        statement.executeUpdate();
        DB.update(QUERY);
    }


    public void onIdSearchSelected(ActionEvent e){
        field_id.setDisable(false);
        field_codigo.setDisable(true);
        field_descricao.setDisable(true);
        field_linha.setDisable(true);
        field_peso.setDisable(true);
    }

    public void onCodeSearchSelected(ActionEvent e){
        field_id.setDisable(true);
        field_codigo.setDisable(false);
        field_descricao.setDisable(true);
        field_linha.setDisable(true);
        field_peso.setDisable(true);
    }


    public void getItem() {
        if(this.radioSearchById.isSelected()) {
            this.queryTerm = field_id.getText();
            this.column = "rowid";
        }
        else if(this.radioSearchById.isSelected()){
            this.queryTerm = field_codigo.getText();
            this.column = "codigo";
        }
        else {
            String msg = "Você deve selecionar um tipo de consulta";
            System.out.println(msg);
        }

        final String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, peso from perfis where " + this.column + " = " + this.queryTerm + ";";

        try {
            ResultSet results = DB.select(SQL_QUERY);

            if (!results.isClosed()) {
                clearForm();
                field_id.setText(String.valueOf(results.getInt("rowid")));
                field_codigo.setText(results.getString("codigo"));
                field_descricao.setText(results.getString("descricao"));
                field_linha.setText(results.getString("linha"));
                field_peso.setText(String.valueOf(results.getDouble("peso")));
            }
            else {
                System.out.println("a consulta nao retornou nada");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearForm() {
        field_id.setText("");
        field_codigo.setText("");
        field_descricao.setText("");
        field_linha.setText("");
        field_peso.setText("");
    }
}
