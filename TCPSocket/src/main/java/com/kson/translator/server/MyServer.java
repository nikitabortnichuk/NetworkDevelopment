package com.kson.translator.server;

import static com.kson.translator.util.StringUtil.removeSpaces;

import com.kson.translator.service.Translator;
import com.kson.translator.util.StringUtil;
import com.kson.translator.service.Translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyServer implements Runnable {

    private Socket socket;

    public MyServer(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try (BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {

            printWriter.println("Hello! It is simple translator where we use TCP Socket. \n"
                    + "Write code of language from which you want to translate, "
                    + "than code of language to which you want to translate and your word. \n"
                    + "Example: en ru Hello");
            printWriter.println();

            String input;
            while ((input = socketInputReader.readLine()) != null) {
                printWriter.println("Translating...");
                System.out.println("\nReceived: " + input);

                String translated = "Translated";
//                String translated = getTranslatedWord(input);

                System.out.println("Translate: " + translated);
                printWriter.println("Translate: " + translated + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
