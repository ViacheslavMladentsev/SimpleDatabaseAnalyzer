package com.mladentsev.analyzer.json;

import com.google.gson.Gson;

import com.mladentsev.analyzer.model.dto.input.InputRequestSearchDTO;
import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class JsonFrom {

    private static final String ERROR_DESERIALIZATION = "error deserialization";

    private static final String ERROR_INCORRECT_INPUT_PATH = "error path";

    private static final String MESSAGE_ERROR_INCORRECT_INPUT_PATH = "по адресу %s файл отсутствует";

    public static Optional<InputRequestSearchDTO> getRequestSearch(String pathInputFile, String pathOutputFile) {
        try {
            Gson gson = new Gson();
            File f = new File(pathInputFile);
            if (f.exists() && !f.isDirectory()) {
                FileReader fr = new FileReader(f);
                fr.close();
                return Optional.of(new InputRequestSearchDTO(gson.fromJson(fr, InputRequestSearchDTO.class).getInputCriteriaSearchDTOS()));
            } else {
                new OutputErrorDTO(ERROR_INCORRECT_INPUT_PATH, String.format(MESSAGE_ERROR_INCORRECT_INPUT_PATH, f)).recordErrorOutput(pathOutputFile);
            }
        } catch (IOException e) {
            new OutputErrorDTO(ERROR_DESERIALIZATION, e.getMessage()).recordErrorOutput(pathOutputFile);
        }
        return Optional.empty();
    }
}
