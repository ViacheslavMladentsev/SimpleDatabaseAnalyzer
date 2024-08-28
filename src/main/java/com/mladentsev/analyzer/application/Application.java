package com.mladentsev.analyzer.application;

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

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        ValidationInputArgument.validInput(args)
                .ifPresent(outputError -> outputError
                        .recordErrorOutput("/home/lieineyes/school21/test_task/SimpleDatabaseAnalyzer/output.json"));

        Analyzer.run(context);

    }

}
