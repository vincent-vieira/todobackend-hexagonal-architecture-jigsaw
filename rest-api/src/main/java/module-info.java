module todobackend.rest.api {
    requires spring.boot;
    requires spring.web;
    requires spring.webmvc;
    requires spring.core;
    requires spring.beans;
    requires com.fasterxml.jackson.databind;
    requires jakarta.validation;
    requires org.hibernate.validator;

    requires todobackend.infrastructure;
    requires transitive todobackend.domain;

    opens io.vieira.todosapi to spring.core, spring.beans;
    opens io.vieira.todosapi.rest to spring.core, spring.beans, spring.webmvc;
    opens io.vieira.todosapi.rest.models to org.hibernate.validator;

    exports io.vieira.todosapi.rest to spring.webmvc, spring.web;
    exports io.vieira.todosapi.rest.models to com.fasterxml.jackson.databind;
}