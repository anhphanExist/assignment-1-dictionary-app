package dictionaryApplication.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/dictionaryApplication/Database/dict_hh.db";
            return DriverManager.getConnection(url);
        }
        catch (Exception e) {
            dictionaryApplication.dictionaryApplication.catchingException(e);
            return null;
        }
    }
}
