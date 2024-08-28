package com.mladentsev.analyzer.model.output;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public class OutputError {

    String type;
    String message;

    public void recordErrorOutput(String pathOutputErrorFile) {
        Path path = Paths.get(pathOutputErrorFile);
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter(String.valueOf(path));
            gson.toJson(this, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(-1);
        }
    }

}
