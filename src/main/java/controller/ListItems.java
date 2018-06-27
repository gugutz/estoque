package controller;

import db.DB;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TableItem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ListItems implements Initializable {


    private ObservableList<TableItem> row = FXCollections.observableArrayList();
    private Router router;
    private List<TableItem> rowList;

    @FXML
    private TableView<TableItem> products_table;
    @FXML
    private TableColumn<ObservableList, Integer> col_id;
    @FXML
    private TableColumn<TableItem, Integer> col_codigo;
    @FXML
    private TableColumn<TableItem, String> col_descricao;
    @FXML
    private TableColumn col_linha;
    @FXML
    private TableColumn col_qtde;
    @FXML
    private TableColumn col_peso;



    private static final String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, qtde, peso from perfis;";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            ResultSet results = DB.select(SQL_QUERY);



            while (results.next()) {
                Integer id = results.getInt("rowid");
                String codigo = results.getString("codigo");
                String descricao = results.getString("descricao");
                String linha = results.getString("linha");
                Integer qtde = results.getInt("qtde");
                Double peso = results.getDouble("peso") * qtde;

                // salvando o objeto que vai ser colocado nas linhas da tabela
                row.add(new TableItem(id, codigo, descricao, linha, qtde, peso));
                rowList.add(new TableItem(id, codigo, descricao, linha, qtde, peso));

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }


        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        col_linha.setCellValueFactory(new PropertyValueFactory<>("linha"));
        col_qtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));
        col_peso.setCellValueFactory(new PropertyValueFactory<>("peso"));


    //FINALLY ADDED TO TableView
    products_table.setItems(row);

    products_table.setContextMenu(createContextMenu());
}

    public TableItem getSelectedItem() {
        TableItem selectedItem = products_table.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.getId());
        return selectedItem;
    }


    public ContextMenu createContextMenu() {
        // building the context menu
        //
        final ContextMenu contextMenu = new ContextMenu();


        // EDIT CONTEXT MENU
        MenuItem item0 = new MenuItem("Editar Item");
        item0.setOnAction((ActionEvent e) -> {
            TableItem row = getSelectedItem();
            int itemId = row.getId();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ItemDetails.fxml"));
            fxmlLoader.setController(new ItemDetails(itemId));
            try {
                fxmlLoader.load();
                System.out.println("About");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem item1 = new MenuItem("Deletar Item");
        item1.setOnAction(e -> System.out.println("About"));
        MenuItem item2 = new MenuItem("Ver detalhes");
        item2.setOnAction(e -> System.out.println("Preferences"));

        // adding the submenu items to the context menu
        contextMenu.getItems().addAll(item0, item1, item2);
        return contextMenu;
    }

}


