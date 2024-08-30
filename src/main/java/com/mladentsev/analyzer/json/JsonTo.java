package com.mladentsev.analyzer.json;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonTo {

    private static final String MESSAGE_EXEPTION_RECORD = "Ошибка записи файла: ";

    private JsonTo() {
    }

    public static void recordOutputJson(Object object, String pathOutputFile) {
        Path path = Paths.get(pathOutputFile);
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter(String.valueOf(path));
            gson.toJson(object, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println(MESSAGE_EXEPTION_RECORD + e.getMessage());
            System.exit(-1);
        }
    }
}
