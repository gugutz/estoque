package main.java.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Properties dbProps = new Properties();
    private static FileInputStream propsFile = null;
    private static Connection connection = null;

    // Database Information
    private static String driverClass = dbProps.getProperty("DB_DRIVER_CLASS");
    private static String host = dbProps.getProperty("DB_URL");
    private static String dbName = dbProps.getProperty("DB_NAME");
    private static String dbUser = dbProps.getProperty("DB_USERNAME");
    private static String dbPassword = dbProps.getProperty("DB_PASSWORD");

    public static Connection connect() throws SQLException {

        driverClass = dbProps.getProperty("DB_DRIVER_CLASS");
        host = dbProps.getProperty("DB_URL");
        dbName = dbProps.getProperty("DB_NAME");
        dbUser = dbProps.getProperty("DB_USERNAME");
        dbPassword = dbProps.getProperty("DB_PASSWORD");
        // Tries to connect
        try {
            propsFile = new FileInputStream("src/db/db.properties");
            dbProps.load(propsFile);

//            String connectString = "jdbc:mariadb://" + host + dbName + "?user=" + dbUser + "&password=" + dbPassword;
            connection = DriverManager.getConnection("jdbc:sqlite:app.db");
//            connection = DriverManager.getConnection(host + dbName, dbUser, dbPassword);
            System.out.println("conected to db succesfully!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erro:" + e);

        }
// finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
        return connection;
    }
}
