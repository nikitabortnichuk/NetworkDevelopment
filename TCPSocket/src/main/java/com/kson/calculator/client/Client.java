package com.kson.calculator.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("127.0.0.1", 9091));
            InputStream inputStream = socket.getInputStream();

            BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            printMessageFromSocket(socketReader);

            String userInput;

            while ((userInput = terminalReader.readLine()) != null) {
                printWriter.println(userInput);

                printMessageFromSocket(socketReader);
            }
        }
    }


    private static void printMessageFromSocket(BufferedReader socketReader) throws IOException {
        String line;
        while ((line = socketReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            System.out.println(line);
        }
    }

}
