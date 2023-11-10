module todobackend.rest.api {
    requires spring.boot;
    requires spring.web;
    requires spring.webmvc;
    requires spring.core;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.databind;
    requires jakarta.validation;
    requires org.hibernate.validator;

    requires todobackend.infrastructure;
    requires transitive todobackend.domain;

    opens io.vieira.todos.rest to spring.core, spring.beans, spring.webmvc;
    opens io.vieira.todos.rest.models to org.hibernate.validator;

    exports io.vieira.todos.rest to spring.web, spring.webmvc;
    exports io.vieira.todos.rest.models to com.fasterxml.jackson.databind;
}