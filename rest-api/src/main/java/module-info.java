module todobackend.rest.api {
    requires spring.beans;
    requires spring.core;

    requires spring.boot.autoconfigure;
    requires spring.boot;

    requires spring.web;
    requires spring.webmvc;
    requires com.fasterxml.jackson.databind;

    requires jakarta.validation;
    requires org.hibernate.validator;

    requires todobackend.infrastructure;
    requires transitive todobackend.domain;

    opens io.vieira.todos.rest to spring.beans, spring.webmvc;
    opens io.vieira.todos.rest.models to org.hibernate.validator, com.fasterxml.jackson.databind;

    exports io.vieira.todos.rest to spring.web;
}