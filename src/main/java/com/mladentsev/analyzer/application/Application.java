package com.mladentsev.analyzer.application;

import com.mladentsev.analyzer.json.AnalyzerJsonReadWrightFile;
import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;
import com.mladentsev.analyzer.validation.ValidationInputArgument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;


@SpringBootApplication
@ComponentScan(basePackages = {"com.mladentsev.analyzer"})
@EntityScan("com.mladentsev.analyzer.model.entity")
@EnableJpaRepositories(basePackages = "com.mladentsev.analyzer.repositories")
public class Application {

    private static final String EMERGENCY_PATH_FOR_OUTPUT_FILE_WITH_ERROR =
            "/home/output.json";

    private static final String ERROR_SQL_EXCEPTION =
            "error SQLException";

    private static ConfigurableApplicationContext CONTEXT;

    public static ConfigurableApplicationContext getContext() {
        return CONTEXT;
    }

    public static void main(String[] args) {

        CONTEXT = SpringApplication.run(Application.class, args);

        ValidationInputArgument.validInput(args)
                .ifPresent(outputErrorDTO -> AnalyzerJsonReadWrightFile.recordOutputJson(outputErrorDTO,
                        EMERGENCY_PATH_FOR_OUTPUT_FILE_WITH_ERROR));

        try {
            Analyzer.run(args);
        } catch (SQLException e) {
            AnalyzerJsonReadWrightFile.recordOutputJson(new OutputErrorDTO(ERROR_SQL_EXCEPTION, e.getMessage()),
                    EMERGENCY_PATH_FOR_OUTPUT_FILE_WITH_ERROR);
        }

    }

}
