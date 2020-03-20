package com.kson.translator.client;

import com.kson.translator.util.StringUtil;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Client {

    @SneakyThrows
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket()) {

            InetAddress address = InetAddress.getLocalHost();

            InputStreamReader isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);

            BufferedReader bufferedReader = new BufferedReader(isr);

            String mainMessage = "Hello! It is simple translator where we use TCP Socket. \n"
                    + "Write code of language from which you want to translate, "
                    + "than code of language to which you want to translate and your word. \n"
                    + "Example: en ru Hello";

            System.out.println(mainMessage);

            byte[] buf;
            DatagramPacket packet;

            String input;
            while ((input = bufferedReader.readLine()) != null) {

                buf = input.getBytes("UTF-8");
                packet = new DatagramPacket(buf, buf.length, address, 9095);
                socket.send(packet);

                buf = new byte[65535];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(StringUtil.convertBytes(buf), "UTF-8");

                System.out.println(received);
            }

//            while ((userInput = terminalReader.readLine()) != null) {
//
//                buf = userInput.getBytes(StandardCharsets.UTF_8);
//                packet = new DatagramPacket(buf, buf.length, address, 9095);
//                socket.send(packet);
//
//                buf = new byte[65535];
//                packet = new DatagramPacket(buf, buf.length);
//                socket.receive(packet);
//                String received = StringUtil.getFromBytes(buf);
//
//                System.out.println(received);
//
//            }
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
