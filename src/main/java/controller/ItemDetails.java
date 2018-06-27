package controller;

import db.DB;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;


public class ItemDetails implements Initializable {

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


    // a classe ToogleGroup agrupa os botoes de radio, e facilitar extrair o item selecionado
    final ToggleGroup searchType = new ToggleGroup();

    private String column;


    // ************************************************
    // Constructors

    public ItemDetails() {
        this.column = "rowid";
        this.id = -1;
    }


    // constructor overload
    public ItemDetails(Integer itemId) {
        this.column = "rowid";
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

        if (this.id >= 0) {
            getItem(this.id);
        }
    }


    public void onBotaoPesquisarClicked(ActionEvent e) {
        int id = Integer.parseInt(field_id.getText());
        getItem(id);
    }


    public void onButtonClearFormClicked(ActionEvent e) {
        clearForm();
    }


    public void onButtonDeleteClicked(ActionEvent e) throws SQLException {
        DB.close();
        String id = field_id.getText();
        final String query = "DELETE FROM perfis WHERE rowid = " + id + ";";
        DB.delete(query);
        clearForm();
    }


    public void onButtonEditClicked(ActionEvent e) throws SQLException {
        DB.close();
        final String QUERY = "UPDATE perfis SET codigo = ?, descricao = ?, linha = ?, qtde = ?, peso = ? WHERE rowid = ?";
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
    }


    public void onIdSearchSelected(ActionEvent e) {
        setSearchType("id");
    }

    public void onCodeSearchSelected(ActionEvent e) {
        setSearchType("code");
    }

    public void setSearchType(String searchType) {
        if (searchType == "id") {
            field_id.setDisable(false);
            field_codigo.setDisable(true);
            field_descricao.setDisable(true);
            field_linha.setDisable(true);
            field_qtde.setDisable(true);
            field_peso.setDisable(true);
        } else if (searchType == "code") {
            field_id.setDisable(true);
            field_codigo.setDisable(false);
            field_descricao.setDisable(true);
            field_linha.setDisable(true);
            field_qtde.setDisable(true);
            field_peso.setDisable(true);
        }
    }


    public void getItem(int id) {
        if (this.radioSearchById.isSelected()) {
            this.queryTerm = field_id.getText();
            this.column = "rowid";
        } else if (this.radioSearchById.isSelected()) {
            this.queryTerm = field_codigo.getText();
            this.column = "codigo";
        } else {
            String msg = "Você deve selecionar um tipo de consulta";
            System.out.println(msg);
        }

        final String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, qtde, peso from perfis where " + this.column + " = " + this.queryTerm + ";";

        try {
            ResultSet results = DB.select(SQL_QUERY);
            if (!results.isClosed()) {

                // enable all fields first
                field_id.setDisable(false);
                field_codigo.setDisable(false);
                field_descricao.setDisable(false);
                field_linha.setDisable(false);
                field_qtde.setDisable(false);
                field_peso.setDisable(false);

                // sets the values retrieved from the database
                this.field_id.setText(String.valueOf(results.getInt("rowid")));
                field_codigo.setText(results.getString("codigo"));
                field_descricao.setText(results.getString("descricao"));
                field_linha.setText(results.getString("linha"));
                field_qtde.setText(String.valueOf(results.getInt("qtde")));
                field_peso.setText(String.valueOf(results.getDouble("peso")));

            } else {
                System.out.println("a consulta nao retornou nada");
                return;
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

        public void clearForm () {
                field_id.setText("");
                field_codigo.setText("");
                field_descricao.setText("");
                field_linha.setText("");
                field_peso.setText("");
        }
    }

