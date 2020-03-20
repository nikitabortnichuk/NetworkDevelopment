package com.kson.translator.server;

import com.kson.translator.service.Translator;
import com.kson.translator.util.StringUtil;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class MyServer implements Runnable {

    private DatagramSocket socket;
    private byte[] buf = new byte[65535];

    public MyServer(DatagramSocket socket) {
        this.socket = socket;
    }


    @SneakyThrows
    @Override
    public void run() {

        DatagramPacket packet;

        while (true) {

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(StringUtil.convertBytes(buf), "UTF-8");

            System.out.println("Received: " + received);

//            String translated = "Переведено";
            String translated = getTranslatedWord(received);

            String message = "Translated: " + translated;
            System.out.println(message);

            packet.setData(message.getBytes("UTF-8"));
            socket.send(packet);

            if(received.equalsIgnoreCase("exit")){
                break;
            }

            buf = new byte[65535];

        }

    }

    private String getTranslatedWord(String input) {
        String[] inputWords = StringUtil.removeSpaces(input).split("\\s");
        System.out.println("From language: " + inputWords[0]);
        System.out.println("To language: " + inputWords[1]);
        System.out.println("Word: " + inputWords[2]);
        return Translator.translate(inputWords[0], inputWords[1], inputWords[2]);
    }
}
