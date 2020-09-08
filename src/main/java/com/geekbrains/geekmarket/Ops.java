package com.geekbrains.geekmarket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Ops {
    public static void main(String[] args) {
        Map<Integer, Objects> obj = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\java\\geek-market\\src\\main\\resources\\objects.txt"));

        String currentLine = null;
        String [] splitline;
        while (true) {
            try {
                if (!((currentLine = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            splitline = currentLine.split("\\s");


            int idf = parseInt(splitline[0]);
            Integer id2f;
            if(splitline[1].equals("null")){
                id2f = null;
            }else {
                id2f = parseInt(splitline[1]);
            }

            obj.put(idf,new Objects(idf,id2f,null));


        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
