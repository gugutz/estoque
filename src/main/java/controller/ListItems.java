package controller;

import db.DB;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.util.Callback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class ListItems implements Initializable {

    @FXML
    private TableView products_table;
    @FXML
    private Label bottomInfoLabel;


    private ObservableList<ObservableList> tableData = FXCollections.observableArrayList();
    private static final String SQL_QUERY = "SELECT rowid, codigo, descricao, linha, peso from perfis;";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // instantiating a router object to change panes when needed
        Router router = new Router();

        try {
            Connection connection = DB.connect();

            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(SQL_QUERY);


            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<results.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(results.getMetaData().getColumnName(i+1));
                 col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                         new SimpleStringProperty(param.getValue().get(j).toString()));

                products_table.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(results.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=results.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(results.getString(i));
                }
                System.out.println("Row added "+row );
                tableData.add(row);


                //
                // building the context menu
                //
                final ContextMenu contextMenu = new ContextMenu();

                // EDIT CONTEXT MENU
                MenuItem item0 = new MenuItem("Editar Item");
                item0.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        try {
                            int id = results.getInt("id");
                            ItemDetails item = new ItemDetails(id);
                            router.newScreen("ItemDetails");
                        } catch (IOException e1) {
                            System.out.println("nao achou tela do itemdetails");
                            e1.printStackTrace();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println("About");
                    }
                });
                MenuItem item1 = new MenuItem("Deletar Item");
                item1.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("About");
                    }
                });
                MenuItem item2 = new MenuItem("Ver detalhes");
                item2.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        System.out.println("Preferences");
                    }
                });

                // adding the submenu items to the context menu
                contextMenu.getItems().addAll(item0, item1, item2);

                // finally adding the context menu do the Items Table
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );
                tableData.setContextMenu(contextMenu);
            }

            //FINALLY ADDED TO TableView
            products_table.setItems(tableData);




        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }




//
//            String id;
//            String codigo;
//            String descricao;
//            String linha;
//            String peso;
//
//
//            while (results.next()) {
//
//                id = String.valueOf(results.getInt("rowid"));
//                codigo = results.getString("codigo");
//                descricao = results.getString("descricao");
//                linha = results.getString("linha");
//                peso = String.valueOf(results.getDouble("peso"));
//
//                // generates the table object using the personalized constructor
//                tableData.add(new TableItem(id, codigo, descricao, linha, peso));
//
//                System.out.println(id + " " + codigo + " " +  descricao + " " + linha + " " +  peso);
//            }
//
//            col_id.setCellValueFactory(new PropertyValueFactory<TableItem, String>("id"));
//            col_codigo.setCellValueFactory(new PropertyValueFactory<TableItem, String>("codigo"));
//            col_descricao.setCellValueFactory(new PropertyValueFactory<TableItem, String>("descricao"));
//            col_linha.setCellValueFactory(new PropertyValueFactory<TableItem, String>("linha"));
//            col_peso.setCellValueFactory(new PropertyValueFactory<TableItem, String>("peso"));
//
//            products_table.setItems(tableData);

//            //encerrando a conexão
//            stmt.close();
//            connection.close();


//        catch (SQLException e) {
//            System.out.println("conexao falhou");
//            Logger.getLogger(ListItems.class.getName()).log(Level.SEVERE, null, e);
//            e.printStackTrace();
//        }



}


