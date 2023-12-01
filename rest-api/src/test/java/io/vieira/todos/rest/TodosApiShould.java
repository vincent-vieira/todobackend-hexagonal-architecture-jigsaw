package io.vieira.todos.rest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.vieira.todos.TodosService;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packagesOf = {
        TodosService.class,
        TodosApiShould.class
})
public class TodosApiShould {

    @ArchTest
    public static final ArchRule respectTheHexagonalArchitecturePattern = onionArchitecture()
            .domainModels("io.vieira.todos..")
            .domainServices("io.vieira.todos..")
            .applicationServices("io.vieira.todos.infrastructure..")
            .adapter("persistence", "io.vieira.todos.infrastructure.data..")
            .adapter("REST API", "io.vieira.todos.rest..");

    @ArchTest
    public static final ArchRule respectTheLayeredArchitecturePattern = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Presentation").definedBy("io.vieira.todos.rest..")
            .layer("Business").definedBy("io.vieira.todos..")
            .layer("Persistence").definedBy("io.vieira.todos..", "io.vieira.todos.infrastructure.data..")
            .whereLayer("Business").mayNotAccessAnyLayer()
            .whereLayer("Presentation").mayOnlyAccessLayers("Business")
            .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
            .whereLayer("Persistence").mayOnlyAccessLayers("Business");
}
