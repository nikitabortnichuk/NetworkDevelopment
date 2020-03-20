package com.kson.translator.server;

import java.io.IOException;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9095);
        System.out.println("Start server");
        new Thread(new MyServer(datagramSocket)).start();
    }
}
