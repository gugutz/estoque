package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    public static Connection connect() throws SQLException {
        Properties props = new Properties();
        FileInputStream propsFile = null;
        Connection connection = null;
        String driverClass = props.getProperty("DB_DRIVER_CLASS");
        String host = props.getProperty("DB_URL");
        String user = props.getProperty("DB_USERNAME");
        String password = props.getProperty("DB_PASSWORD");

        try {
            propsFile = new FileInputStream("src/database/db.properties");
            props.load(propsFile);

            String connectString = String.format("jdbc:mariadb://%s/DB?user=%s&password=%s", host, user, password );
//            connection = DriverManager.connect("jdbc:mariadb://localhost:3306/DB?user=root&password=manenos");
            connection = DriverManager.getConnection(connectString);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erro:" + e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return connection;
    }
}
