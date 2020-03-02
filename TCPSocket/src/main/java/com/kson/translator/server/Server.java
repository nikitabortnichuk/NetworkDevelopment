package com.kson.translator.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9092)) {
            while (true) {
                System.out.println("Start server");

                Socket socket = serverSocket.accept();
                new Thread(new MyServer(socket)).start();
            }
        }
    }
}
