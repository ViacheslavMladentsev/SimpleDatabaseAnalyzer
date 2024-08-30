package com.mladentsev.analyzer.validation;


import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;

import java.io.File;
import java.util.Optional;


public class ValidationInputArgument {

    private static final String SEARCH_OPTION = "search";

    private static final String STAT_OPTION = "stat";

    private static final String ERROR_OPTION = "error options";

    private static final String MESSAGE_ERROR_OPTION = "некорректно задана операция";

    private static final String ERROR_INPUT_PATH = "error input path";

    private static final String MESSAGE_ERROR_INPUT_PATH = "по указанному пути %s файл отсутствует";

    private static final String ERROR_COUNT_ARGUMENT = "error count argument";

    private static final String MESSAGE_ERROR_COUNT_ARGUMENT = "некорректное количество аргументов запуска программы";


    private ValidationInputArgument() {
    }

    public static Optional<OutputErrorDTO> validInput(String[] args) {
        if (args.length == 3) {
            if (!SEARCH_OPTION.equals(args[0]) && !STAT_OPTION.equals(args[0])) {
                return Optional.of(new OutputErrorDTO(ERROR_OPTION, MESSAGE_ERROR_OPTION));
            }

            File file = new File(args[1]);
            if (!file.exists()) {
                return Optional.of(new OutputErrorDTO(ERROR_INPUT_PATH, String.format(MESSAGE_ERROR_INPUT_PATH, file)));
            }
            return Optional.empty();
        } else {
            return Optional.of(new OutputErrorDTO(ERROR_COUNT_ARGUMENT, MESSAGE_ERROR_COUNT_ARGUMENT));

        }
    }

}
