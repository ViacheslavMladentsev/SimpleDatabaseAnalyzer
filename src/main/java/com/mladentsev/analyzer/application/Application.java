package com.mladentsev.analyzer.application;

import com.mladentsev.analyzer.json.JsonTo;
import com.mladentsev.analyzer.validation.ValidationInputArgument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.mladentsev.analyzer"})
@EntityScan("com.mladentsev.analyzer.model.entity")
@EnableJpaRepositories(basePackages = "com.mladentsev.analyzer.repositories")
public class Application {

    //TODO рассмотреть возхможность загрузки значения из файла с настройками
    private static final String EMERGENCY_PATH_FOR_OUTPUT_FILE_WITH_ERROR = "/home/lieineyes/school21/SimpleDatabaseAnalyzer/output.json";

    private static ConfigurableApplicationContext CONTEXT;

    public static ConfigurableApplicationContext getContext() {
        return CONTEXT;
    }

    public static void main(String[] args) {

        CONTEXT = SpringApplication.run(Application.class, args);

        ValidationInputArgument.validInput(args)
                .ifPresent(outputErrorDTO-> JsonTo.recordOutputJson(outputErrorDTO,
                        EMERGENCY_PATH_FOR_OUTPUT_FILE_WITH_ERROR));

        Analyzer.run(args);

    }

}
