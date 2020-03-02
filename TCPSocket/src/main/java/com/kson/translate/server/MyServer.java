package com.kson.translate.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyServer implements Runnable {

    Socket socket;

    public MyServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {

            printWriter.println("Hello! This online calculator. You can write an expression and get the result.");

            printWriter.println("For example: 7 * 8 . Type \"exit\" to end a program.");

            String message;
            while ((message = socketInputReader.readLine()) != null) {

                System.out.print("received: " + message + "\n");

                printWriter.println("Solving...");

                try {
                    String solution = solve(message);
                    printWriter.println("Solution: " + solution);
                    System.out.println("sent: " + solution);
                } catch (Exception e) {
                    printWriter.println(e.getMessage());
                }
                printWriter.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String[] parseExpression(String expression) {
        if (!expression.matches("\\d+\\s[+\\-*/]\\s\\d+")) {
            throw new RuntimeException("Wrong input");
        }
        return expression.split(" ");
    }

    private String solve(String expression) {

        String[] characters = parseExpression(expression);

        double firstParameter = Double.parseDouble(characters[0]);

        String arithmetic = characters[1];

        double secondParameter = Double.parseDouble(characters[2]);

        return String.valueOf(calculateByArithmetic(arithmetic, firstParameter, secondParameter));
    }

    private double calculateByArithmetic(String arithmetic, double firstParam, double secondParam) {

        double result = 0;

        switch (arithmetic) {
            case "+":
                result = add(firstParam, secondParam);
                break;
            case "-":
                result = sub(firstParam, secondParam);
                break;
            case "*":
                result = multi(firstParam, secondParam);
                break;
            case "/":
                result = div(firstParam, secondParam);
                break;
        }

        return result;

    }

    private double add(double firstParam, double secondParam) {
        return firstParam + secondParam;
    }

    private double sub(double firstParam, double secondParam) {
        return firstParam - secondParam;
    }

    private double multi(double firstParam, double secondParam) {
        return firstParam * secondParam;
    }

    private double div(double firstParam, double secondParam) {
        if (secondParam == 0) {
            throw new RuntimeException("Division by zero!");
        }
        return firstParam / secondParam;
    }
}
