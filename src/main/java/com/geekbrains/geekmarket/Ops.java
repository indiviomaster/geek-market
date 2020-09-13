package com.geekbrains.geekmarket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Ops {
    public static void main(String[] args) {
        List <Objects> listObjects = new ArrayList<>();
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

            int objId = parseInt(splitline[0]);
            Integer objParentId;
            if(splitline[1].equals("null")){
                objParentId = null;
            }else {
                objParentId = parseInt(splitline[1]);
            }

            if (objParentId!=null){
                for(Objects obj : listObjects)
                {
                    if(obj.getId() == objParentId){
                        Objects chd = new Objects(objId,objParentId,null);
                        obj.addChildren(chd);
                    } else {
                       if(obj.getChildrenList()!= null) {
                           for (Objects ch : obj.getChildrenList()) {
                               if (ch != null && ch.getId() == objParentId){
                                   ch.addChildren(new Objects(objId, objParentId, null));
                               }
                           }
                       }
                    }
                }
            }else{
                listObjects.add(new Objects(objId,objParentId,null));
            }

        }
            System.out.println(listObjects);
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
