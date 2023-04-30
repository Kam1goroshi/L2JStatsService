package com.l2eminence;
import java.util.Base64;
import java.util.Properties;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.*;
import org.json.JSONObject;
public class App {
    public static void main(String[] args) {
        myServer server = new myServer(7777);
        try{
        server.run();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(getImportantStatistics());
    }
}
