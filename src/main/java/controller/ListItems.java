package controller;

import db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;


public class ListItems {
    @FXML private TableView<User> Products;
    @FXML private TableColumn<User, String> productIdColumn;
    @FXML private TableColumn<User, String> productNameColumn;
    @FXML private TableColumn<User, String> productDescriptionColumn;


    private static final String QUERY = "SELECT id,descricao,linha from perfis ORDER DESC";

        public void list() {
        //using try-with-resources to avoid closing resources (boiler plate code)
        try {
            Connection con = DB.connect();
            System.out.println("conectado");
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(QUERY);


        while(results.next()){
            int id = results.getInt("id");
            String name = results.getString("descricao");
            String description = results.getString("linha");

            productIdColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
            productNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
            productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<User, String>("active"));

            tableView.getItems().setAll(parseUserList());

            System.out.println(id + "," +name+ "," +description+ ",");
            }


                // begin stack overflow code
                List<Integer> intValues = Arrays.asList(1, 2, 3, 4, 5);
                List<String> stringValues = Arrays.asList("One", "Two", "Three", "Four", "Five");

                TableView<Integer> table = new TableView<>();
                for (int i = 0; i < intValues.size() && i < stringValues.size(); i++) {
                    table.getItems().add(i);
                }

                TableColumn<Integer, Number> intColumn = new TableColumn<>("Value");
                intColumn.setCellValueFactory(cellData -> {
                    Integer rowIndex = cellData.getValue();
                    return new ReadOnlyIntegerWrapper(intValues.get(rowIndex));
                });

                TableColumn<Integer, String> nameColumn = new TableColumn<>("Name");
                nameColumn.setCellValueFactory(cellData -> {
                    Integer rowIndex = cellData.getValue();
                    return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
                });

                table.getColumns().add(intColumn);
                table.getColumns().add(nameColumn);
                // end of stackoverflow code

        } catch (SQLException e) {
            System.out.println("conexao falhou");
            e.printStackTrace();
        }
    }
}

