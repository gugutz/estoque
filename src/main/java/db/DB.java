package db;

import java.sql.*;
import java.util.Properties;

public class DB {

    private static Properties dbProps = new Properties();
    private static Connection connection = null;


    public static Connection connect() throws SQLException {

        //            FileInputStream propsFile = new FileInputStream("src/main/resources/db/db.properties");
//            dbProps.load(propsFile);
        connection = DriverManager.getConnection("jdbc:sqlite::resource:db/app.db");

        if(connection != null) {
            System.out.println("conected to db succesfully!");
        }
        else {
            System.out.println("problema na conexao com banco de dados");
        }


        return connection;
    }

    public static void close() throws SQLException {
        connection.close();
    }

    // adds method to execute a query e returns a resultset
    public static ResultSet select(String query) throws SQLException {
        Connection connection = DB.connect();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        return results;
    }

    public static ResultSet delete(String query) throws SQLException {
        Connection connection = DB.connect();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        return results;
    }

    public static void update(String query) throws SQLException {
        Connection connection = DB.connect();
        Statement stmt = connection.createStatement();
    }
}
