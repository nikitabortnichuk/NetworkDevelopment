package com.kson.calculator.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(9091)){
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new MyServer(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
