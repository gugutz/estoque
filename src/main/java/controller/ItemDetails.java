package controller;

import db.DB;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;


public class ItemDetails extends BorderPane implements Initializable {

    //TODO for some reason the Spinner element cannot have a snake_cased name, so i had to cammelCase only that specific field. go figure...
    @FXML
    public TextField field_id, field_codigo, field_descricao, field_linha, field_qtde, field_peso;
    @FXML
    public Button buttonEdit, buttonDelete, botaoPesquisar, buttonClearForm;
    // Creating the radio buttons that will settle the type of search (id or name)
    @FXML
    RadioButton radioSearchById, radioSearchByCode;


    private Integer id;
    private String code;
    private String queryTerm;
    private String column;


    // a classe ToogleGroup agrupa os botoes de radio, e facilitar extrair o item selecionado
    final ToggleGroup searchType = new ToggleGroup();



    // ************************************************
    // Constructors

    public ItemDetails() {
        this.column = null;
        this.id = null;
    }


    // constructor overload
    public ItemDetails(Integer itemId) {
        this.id = itemId;
        this.queryTerm = String.valueOf(this.id);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioSearchById.setToggleGroup(searchType);
        radioSearchById.setSelected(true);
        radioSearchById.setToggleGroup(searchType);
        radioSearchByCode.setToggleGroup(searchType);


        setSearchType("id");

        if (this.id != null) {
            getItem();
        }
    }


    public void onBotaoPesquisarClicked(ActionEvent e) {
//        int id = Integer.parseInt(field_id.getText());
        if (this.radioSearchById.isSelected()) {
            this.queryTerm = field_id.getText();
            this.column = "rowid";

        }
        if (this.radioSearchByCode.isSelected()) {
            this.queryTerm = field_codigo.getText();
            this.column = new String("codigo");
        }
        if (this.id != null) {
            this.column = "rowid";
            this.queryTerm = String.valueOf(this.id);
        }
        getItem();
    }


    public void onButtonClearFormClicked(ActionEvent e) {
        clearForm();
    }


    public void onButtonDeleteClicked(ActionEvent e) throws SQLException {
        DB.close();
        String id = field_id.getText();
        String query = "DELETE FROM perfis WHERE rowid = " + id + ";";
        DB.delete(query);
        clearForm();
        DB.close();
    }


    public void onButtonEditClicked(ActionEvent e) throws SQLException {
        DB.close();
        String QUERY = "UPDATE perfis SET codigo = ?, descricao = ?, linha = ?, qtde = ?, peso = ? WHERE rowid = ?";
        Connection connection = DB.connect();
        PreparedStatement statement = connection.prepareStatement(QUERY);

        statement.setString(1, field_codigo.getText());
        statement.setString(2, field_descricao.getText());
        statement.setString(3, field_linha.getText());
        statement.setString(4, field_qtde.getText());
        statement.setDouble(5, Double.parseDouble(field_peso.getText()));
        statement.setInt(6, Integer.parseInt(field_id.getText()));

        statement.executeUpdate();
        DB.update(QUERY);
        DB.close();
    }


    public void onIdSearchSelected(ActionEvent e) {
        setSearchType("id");
    }

    public void onCodeSearchSelected(ActionEvent e) {
        setSearchType("code");
    }

    public void setSearchType(String searchType) {
        if (searchType == "id") {
            clearForm();
            this.column = "rowid";
            field_id.setDisable(false);
            field_codigo.setDisable(true);
            field_descricao.setDisable(true);
            field_linha.setDisable(true);
            field_qtde.setDisable(true);
            field_peso.setDisable(true);
        } else if (searchType == "code") {
            clearForm();
            this.column = "codigo";
            field_id.setDisable(true);
            field_codigo.setDisable(false);
            field_descricao.setDisable(true);
            field_linha.setDisable(true);
            field_qtde.setDisable(true);
            field_peso.setDisable(true);
        }
    }

    public void enableFields() {
        field_id.setDisable(false);
        field_codigo.setDisable(false);
        field_descricao.setDisable(false);
        field_linha.setDisable(false);
        field_qtde.setDisable(false);
        field_peso.setDisable(false);
    }


    public void getItem() {

        ResultSet results;
        String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, qtde, peso FROM perfis WHERE " + this.column + " = " + this.queryTerm + ";";
        try {
            System.out.println("Coluna sendo usada para pesquisa: " + this.column);
            System.out.println("Termo de pesquisa sendo usado: " + this.queryTerm);
            results = DB.select(SQL_QUERY);
            if (!results.isClosed()) {

                enableFields();

                // sets the values retrieved from the database
                this.field_id.setText(String.valueOf(results.getInt("rowid")));
                field_codigo.setText(results.getString("codigo"));
                field_descricao.setText(results.getString("descricao"));
                field_linha.setText(results.getString("linha"));
                field_qtde.setText(String.valueOf(results.getInt("qtde")));
                field_peso.setText(String.valueOf(results.getDouble("peso")));

            } else {
                System.out.println("A consulta nao retornou nada");
                return;
            }

            results.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

        public void clearForm () {
                field_id.clear();
                field_codigo.clear();
                field_descricao.clear();
                field_linha.clear();
                field_qtde.clear();
                field_peso.clear();
        }
    }

