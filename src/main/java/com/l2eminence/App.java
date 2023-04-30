package com.l2eminence;
public class App {
    public static void main(String[] args) {
        MyServer server = new MyServer(7777);
        try{
        server.run();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
