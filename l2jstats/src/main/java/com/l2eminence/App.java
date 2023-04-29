package com.l2eminence;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class App {
    public static void main(String[] args) {
        Connection con = null;
        try{con = getConnection();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Connection getConnection() throws SQLException{ 
        Connection connection = null;
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/properties/database.properties");
        Properties db_properties = new Properties();
        try{
        db_properties.load(input);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://");
        sb.append(db_properties.getProperty("address")).append(":");
        sb.append(db_properties.getProperty("port")).append("/");
        sb.append(db_properties.getProperty("database"));
        try{
        connection = DriverManager.getConnection(sb.toString(), db_properties.getProperty("user"), db_properties.getProperty("password"));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        if(connection == null){
            throw new SQLException("Failed to establish mysql connection for unknown reason. Are the credentials correct?");
        }
        return connection;
    }
}
