package dictionaryApplication.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private String url;

    public ConnectionDatabase(String _url) {
        this.url = _url;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);
        }
        catch (Exception e) {
            dictionaryApplication.dictionaryApplication.catchingException(e);
            return null;
        }
    }
}
