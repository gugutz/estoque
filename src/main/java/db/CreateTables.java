package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    // connect to db
    private Connection connection = null;
    private Statement stmt;

    public CreateTables() throws SQLException {
        stmt = connection.createStatement();
        connection = DB.connect();
    }

    public void generateSystemTables() throws SQLException {
        stmt.executeUpdate("CREATE TABLE a (id int not null primary key, value varchar(20))");
        stmt.close();
        connection.close();
    }
}
