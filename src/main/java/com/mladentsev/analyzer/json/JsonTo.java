package com.mladentsev.analyzer.json;


import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;


public class JsonTo {

    private static final String MESSAGE_EXCEPTION_RECORD = "Ошибка записи файла: ";

    private JsonTo() {
    }

    public static void recordOutputJson(Object object, String pathOutputFile) {
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter(pathOutputFile);
            gson.toJson(object, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println(MESSAGE_EXCEPTION_RECORD + e.getMessage());
        } finally {
            System.exit(-1);
        }
    }
}
