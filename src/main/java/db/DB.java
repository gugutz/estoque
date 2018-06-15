package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Properties dbProps = new Properties();
    private static Connection connection = null;

    public static Connection connect() throws SQLException {

        // Database Information
        String driverClass = dbProps.getProperty("DB_DRIVER_CLASS");
        String host = dbProps.getProperty("DB_URL");
        String dbName = dbProps.getProperty("DB_NAME");
        String dbUser = dbProps.getProperty("DB_USERNAME");
        String dbPassword = dbProps.getProperty("DB_PASSWORD");

        // Tries to connect
        try {
            FileInputStream propsFile = new FileInputStream("src/main/resources/db/db.properties");
            dbProps.load(propsFile);

//            String connectString = "jdbc:mariadb://" + host + dbName + "?user=" + dbUser + "&password=" + dbPassword;
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/app.db");
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
