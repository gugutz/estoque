package controller;

import db.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.TableItem;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ItemDetails implements Initializable {

    @FXML
    public TextField field_id, field_codigo, field_descricao, field_linha, field_peso;
    @FXML
    public Button botaoVoltar, botaoSalvar, botaoCancelar;

    private int id;

    private ObservableList<ObservableList> tableData = FXCollections.observableArrayList();

    public ItemDetails() {

    }

    public ItemDetails(int id) throws SQLException {
        this.id = id;
        new ItemDetails();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        final String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, peso from perfis where rowid = " + this.id + ";";
        final String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, peso from perfis where rowid = '1'';";

        try {
            Connection connection = DB.connect();

            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(SQL_QUERY);

            field_id.setText(String.valueOf(results.getInt("rowid")));
            field_codigo.setText(results.getString("codigo"));
            field_descricao.setText(results.getString("descricao"));
            field_linha.setText(results.getString("linha"));
            field_peso.setText(String.valueOf(results.getDouble("peso")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
