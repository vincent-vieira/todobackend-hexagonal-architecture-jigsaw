package io.vieira.todos.infrastructure.data;

import io.vieira.todos.TodosRepository;
import io.vieira.todos.infrastructure.data.jpa.TodoEntity;
import io.vieira.todos.infrastructure.data.jpa.TodosJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;

@Configuration(proxyBeanMethods = false)
@EntityScan(basePackageClasses = TodoEntity.class)
@EnableJpaRepositories(basePackageClasses = TodosJpaRepository.class)
public class TodosPersistenceConfiguration {

    @Bean
    TodosRepository delegatingJpaTodosRepository(TodosJpaRepository jpaRepository) {
        return new DelegatingJpaTodosRepository(jpaRepository);
    }
}
