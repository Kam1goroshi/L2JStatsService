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

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

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

    private synchronized static void updateCache() {
        try {
            Connection con = getConnection();
            updateTop100Pvp(con);
            updateTop100Pk(con);
            updateOnline(con);
            updateTop100Clans(con);
            con.close();
        } catch (Exception e) {
            System.err.println("Failed to update cache!");
            HorribleCache.resetTimeStamp();
            e.printStackTrace();
        }
    }

    @RequestMapping("/top100Pvp")
    public String getTop100Pvp() {
        if (HorribleCache.shouldUpdateCache()) {
            updateCache();
        }
        return HorribleCache.getValue("top100Pvp");
    }

    private static void updateTop100Pvp(Connection con) throws Exception {
        JSONArray json = new JSONArray();
        Statement stmt = con.createStatement();
        ResultSet replies = stmt
                .executeQuery("SELECT char_name, pvpkills FROM characters ORDER BY pvpkills LIMIT 100");
        while (replies.next()) {
            JSONObject temp = new JSONObject();
            temp.put(replies.getString(1), replies.getString(2));
            json.add(0, temp);
        }
        HorribleCache.putValue("top100Pvp", json.toString());
        replies.close();
        stmt.close();
    }

    @RequestMapping("/top100Pk")
    public String getTop100Pk() {
        if (HorribleCache.shouldUpdateCache()) {
            updateCache();
        }
        return HorribleCache.getValue("top100Pk");
    }

    private static void updateTop100Pk(Connection con) throws Exception {
        JSONArray json = new JSONArray();
        Statement stmt = con.createStatement();
        ResultSet replies = stmt
                .executeQuery("SELECT char_name, pvpkills FROM characters ORDER BY pkkills LIMIT 100");
        while (replies.next()) {
            JSONObject temp = new JSONObject();
            temp.put(replies.getString(1), replies.getString(2));
            json.add(0, temp);
        }
        HorribleCache.putValue("top100Pk", json.toString());
        replies.close();
        stmt.close();
    }

    @RequestMapping("/online")
    private String getOnlineStatus() {
        if (HorribleCache.shouldUpdateCache()) {
            updateCache();
        }
        return HorribleCache.getValue("online");
    }

    private static void updateOnline(Connection con) throws Exception {
        JSONObject json = new JSONObject();
        Statement stmt = con.createStatement();
        ResultSet replies = stmt.executeQuery("SELECT COUNT(*) FROM characters WHERE online=1");
        replies.next(); // only 1 return value from COUNT()
        json.put("online",replies.getString(1));
        replies.close();
        stmt.close();
        stmt = con.createStatement();
        replies = stmt.executeQuery("SELECT COUNT(*) FROM characters WHERE online=0");
        replies.next(); // only 1 return value from COUNT()
        json.put("offline",replies.getString(1));
        HorribleCache.putValue("online", json.toString());
        replies.close();
        stmt.close();
    }

    @RequestMapping("/top100Clans")
    private String getTop100Clans() {
        if (HorribleCache.shouldUpdateCache()) {
            updateCache();
        }
        return HorribleCache.getValue("top100Clans");
    }

    private static void updateTop100Clans(Connection con) throws Exception {
        JSONArray json = new JSONArray();
        Statement stmt = con.createStatement();
        ResultSet replies = stmt
                .executeQuery("SELECT clan_name, reputation_score FROM clan_data ORDER BY reputation_score LIMIT 100");
        while (replies.next()) {
            JSONObject temp = new JSONObject();
            temp.put(replies.getString(1), replies.getString(2));
            json.add(0, temp);
        }
        HorribleCache.putValue("top100Clans", json.toString());
        replies.close();
        stmt.close();
    }
}
