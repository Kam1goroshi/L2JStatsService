package com.l2eminence;

import java.util.Properties;
import java.io.InputStream;
import java.sql.*;
import org.json.JSONObject;
import org.json.JSONException;

public class App {
    public static void main(String[] args) {
        System.out.println(getOnlineStatus().toString());
    }
    /**
     * @return A connection with the db based on local.properties
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        Connection connection = null;
        InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("resources/properties/database.properties");
        Properties db_properties = new Properties();
        try {
            db_properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("jdbc:mysql://");
            sb.append(db_properties.getProperty("address")).append(":");
            sb.append(db_properties.getProperty("port")).append("/");
            sb.append(db_properties.getProperty("database"));
            connection = DriverManager.getConnection(sb.toString(), db_properties.getProperty("user"),
                    db_properties.getProperty("password"));
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (connection == null) {
            throw new SQLException(
                    "Failed to establish mysql connection for unknown reason. Are the credentials correct?");
        }
        return connection;
    }

    private static JSONObject getOnlineStatus(){
        int online = 0;
        int offline = 0;
        JSONObject json = new JSONObject();
        try {
            Connection con = getConnection();
            ResultSet replies = con.createStatement().executeQuery("SELECT COUNT(*) FROM CHARACTERS WHERE online=1");
            replies.next(); //only 1 return value from COUNT()
            json.put("online", replies.getInt(1));
            replies.close();
            replies = con.createStatement().executeQuery("SELECT COUNT(*) FROM CHARACTERS WHERE online=0");
            replies.next();
            json.put("offline", replies.getInt(1));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return json;
    }
}
