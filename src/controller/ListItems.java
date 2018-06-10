package controller;

import database.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListItems {

    private static final String QUERY = "SELECT id,descricao,linha from perfis ORDER DESC";

        public void list() {
        //using try-with-resources to avoid closing resources (boiler plate code)
        try {
            Connection con = DB.connect();
            System.out.println("conectado");
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery(QUERY);

            int id = results.getInt("id");
            String name = results.getString("descricao");
            String email = results.getString("linha");
            System.out.println(id + "," +name+ "," +email  );

        } catch (SQLException e) {
            System.out.println("conexao falhou");
            e.printStackTrace();
        }
    }
}
