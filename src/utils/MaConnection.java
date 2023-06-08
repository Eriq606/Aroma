package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MaConnection{
    public static Connection getConnection(String database, String username, String password) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection connect=DriverManager.getConnection("jdbc:postgresql://localhost/"+database, username, password);
        connect.setAutoCommit(false);
        return connect;
    }
}