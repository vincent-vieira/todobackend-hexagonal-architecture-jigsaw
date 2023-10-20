package io.vieira.todos.infrastructure;

import io.vieira.todos.RepositoryBasedTodosService;
import io.vieira.todos.TodosRepository;
import io.vieira.todos.TodosService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TodosInfrastructureConfiguration {

    @Bean
    TodosService todosService(TodosRepository todosRepository) {
        return new RepositoryBasedTodosService(todosRepository);
    }
}
