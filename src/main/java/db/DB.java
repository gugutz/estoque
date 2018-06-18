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
//        String driverClass = dbProps.getProperty("DB_DRIVER_CLASS");
//        String host = dbProps.getProperty("DB_URL");
//        String dbName = dbProps.getProperty("DB_NAME");
//        String dbUser = dbProps.getProperty("DB_USERNAME");
//        String dbPassword = dbProps.getProperty("DB_PASSWORD");

        // Tries to connect
        try {
            FileInputStream propsFile = new FileInputStream("src/main/resources/db/db.properties");
            dbProps.load(propsFile);

            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/app.db");
//            connection = DriverManager.getConnection(host + dbName, dbUser, dbPassword);
            if(connection != null) {
                System.out.println("conected to db succesfully!");
            }
            else {
                System.out.println("problema na conexao com banco de dados");
            }

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
