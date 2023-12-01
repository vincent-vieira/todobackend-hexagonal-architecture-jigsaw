module todobackend.infrastructure {
    requires java.base;

    requires spring.data.jpa;
    requires spring.data.commons;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    requires spring.boot.autoconfigure;
    requires spring.context;

    requires todobackend.domain;

    opens io.vieira.todos.infrastructure to spring.core, spring.beans;
    exports io.vieira.todos.infrastructure to todobackend.rest.api;

    opens io.vieira.todos.infrastructure.data to spring.core, spring.beans;
    exports io.vieira.todos.infrastructure.data to todobackend.rest.api;

    opens io.vieira.todos.infrastructure.data.jpa to spring.core, org.hibernate.orm.core;
}