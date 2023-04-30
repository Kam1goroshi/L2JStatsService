package com.l2eminence;

import java.util.Properties;
import java.io.InputStream;
import java.sql.*;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
        System.out.println(getImportantStatistics());
    }

    private static JSONObject getImportantStatistics(){
        JSONObject json = new JSONObject();
        getOnlineStatus(json);
        getTop50Pvp(json);
        getTop50Pk(json);
        return json;
    }

    /**
     * @return A connection with the db based on local.properties
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        Connection connection = null;
        InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("properties/database.properties");
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

    private static void getTop50Pvp(JSONObject json) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt.executeQuery("SELECT char_name, pvpkills FROM characters ORDER BY pvpkills LIMIT 50");
            while (replies.next()) 
                json.append("pvp", new String[] { replies.getString(1), replies.getString(2) });
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    private static void getTop50Pk(JSONObject json) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt.executeQuery("SELECT char_name, pvpkills FROM characters ORDER BY pkkills LIMIT 50");
            while (replies.next()) 
                json.append("pk", new String[] { replies.getString(1), replies.getString(2) });
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void getOnlineStatus(JSONObject json) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt.executeQuery("SELECT COUNT(*) FROM CHARACTERS WHERE online=1");
            replies.next(); // only 1 return value from COUNT()
            json.put("online", replies.getString(1));
            stmt.close();
            replies.close();
            stmt = con.createStatement();         
            replies = stmt.executeQuery("SELECT COUNT(*) FROM CHARACTERS WHERE online=0");
            replies.next();
            json.put("offline", replies.getString(1));
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
