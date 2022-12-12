package com.example.DB_CONNECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_connect {
    
    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try{
                String db = "jdbc:oracle:thin:@localhost:1521:xe";
                connection = DriverManager.getConnection(db, "chat_app_db", "chatapp");
            } catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
        return connection;
    }
}
