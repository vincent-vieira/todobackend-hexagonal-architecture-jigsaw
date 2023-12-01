package io.vieira.todos.rest;

import io.vieira.todos.infrastructure.TodosInfrastructureConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {TodosInfrastructureConfiguration.class, TodosBackendApplication.class}, proxyBeanMethods = false)
public class TodosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosBackendApplication.class, args);
    }
}
