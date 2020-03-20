package com.kson.translator.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    private final static String REMOVE_SPACES = "\\s+";

    private StringUtil() {

    }

    public static String removeSpaces(String input) {
        return input.replaceAll(REMOVE_SPACES, " ").trim();
    }

    public static byte[] convertBytes(byte[] buf){

        if (buf == null){
            throw new RuntimeException("buf is null");
        }


        List<Byte> byteList = new ArrayList<>();

        int i = 0;
        while (buf[i] != 0){
            byteList.add(buf[i]);
            i++;
        }


        byte[] temp = new byte[byteList.size()];

        for (int j = 0; j < temp.length; j++) {
            temp[j] = byteList.get(j);
        }

        return temp;
    }

}
