package com.mladentsev.analyzer.validation;

import com.mladentsev.analyzer.model.output.OutputError;

import java.io.File;
import java.util.Optional;


public class ValidationInputArgument {

    private static final String SEARCH_OPTION = "search";
    // todo вынести константы

    private ValidationInputArgument() {
    }

    public static Optional<OutputError> validInput(String[] args) {
        if (args.length == 3) {
            if (!SEARCH_OPTION.equals(args[0]) && !args[0].equals("stat")) {
                return Optional.of(new OutputError("error options", "некорректно задана операция"));
            }

            File file = new File(args[1]);
            if (!file.exists()) {
                return Optional.of(new OutputError("error input path", "по указанному пути " + file + " файл отсутствует"));
            }
            return Optional.empty();
        } else {
            return Optional.of(new OutputError("error", "некорректное количество аргументов запуска программы"));

        }
    }

}
