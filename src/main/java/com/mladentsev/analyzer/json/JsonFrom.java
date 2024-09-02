package com.mladentsev.analyzer.json;

import com.google.gson.Gson;

import com.mladentsev.analyzer.model.dto.input.InputRequestSearchDTO;
import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class JsonFrom {

    private static final String SEARCH = "search";

    private static final String STATISTICS = "stat";

    private static final String ERROR_DESERIALIZATION = "error read file";

    private static final String MESSAGE_ERROR_INCORRECT_INPUT_PATH = "по адресу %s файл отсутствует";

    private JsonFrom() {
    }

    public static Optional<Object> readInputJson(String operation, String pathInputFile, String pathOutputFile) {
        Gson gson = new Gson();
        File f = new File(pathInputFile);
        try {
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                if (operation.equals(SEARCH)) {
                    return Optional.of(new InputRequestSearchDTO(gson.fromJson(fr, InputRequestSearchDTO.class).getCriterias()));
                } else if (operation.equals(STATISTICS)) {
                    return Optional.of(new InputRequestSearchDTO(gson.fromJson(fr, InputRequestSearchDTO.class).getCriterias()));
                }
                fr.close();
            } else {
                JsonTo.recordOutputJson(new OutputErrorDTO(ERROR_DESERIALIZATION, String.format(MESSAGE_ERROR_INCORRECT_INPUT_PATH, f)), pathOutputFile);
            }
        } catch (IOException e) {
            JsonTo.recordOutputJson(new OutputErrorDTO(ERROR_DESERIALIZATION, e.getMessage()), pathOutputFile);
        }
        return Optional.empty();
    }
}
