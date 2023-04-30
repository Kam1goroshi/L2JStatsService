package com.l2eminence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
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

    @RequestMapping("/top100Pvp")
    public String getTop100Pvp() {
        StringBuilder sb = new StringBuilder();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt
                    .executeQuery("SELECT char_name, pvpkills FROM characters ORDER BY pvpkills LIMIT 100");
            while (replies.next())
                sb.append(replies.getString(1)).append(":").append(replies.getString(2)).append("\n");
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sb.toString();
    }

    @RequestMapping("/top100Pk")
    public String getTop100Pk() {
        StringBuilder sb = new StringBuilder();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt
                    .executeQuery("SELECT char_name, pvpkills FROM characters ORDER BY pkkills LIMIT 100");
            while (replies.next())
                sb.append(replies.getString(1)).append(":").append(replies.getString(2)).append("\n");
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sb.toString();
    }
    @RequestMapping("/online")
    private static String getOnlineStatus() {
        StringBuilder sb = new StringBuilder();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt.executeQuery("SELECT COUNT(*) FROM CHARACTERS WHERE online=1");
            replies.next(); // only 1 return value from COUNT()
            sb.append("online:").append(replies.getString(1)).append("\n");
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sb.toString();
    }

    @RequestMapping("/offline")
    private static String getOfflineStatus() {
        StringBuilder sb = new StringBuilder();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet replies = stmt.executeQuery("SELECT COUNT(*) FROM CHARACTERS WHERE online=0");
            replies.next(); // only 1 return value from COUNT()
            sb.append("offline:").append(replies.getString(1)).append("\n");
            replies.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return sb.toString();
    }

}
