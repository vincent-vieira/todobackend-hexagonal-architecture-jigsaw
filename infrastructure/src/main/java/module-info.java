module todobackend.infrastructure {
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.base;

    requires todobackend.domain;

    opens io.vieira.todos.infrastructure to spring.core, spring.beans;
    exports io.vieira.todos.infrastructure;

    opens io.vieira.todos.infrastructure.data to spring.core, spring.beans;
    exports io.vieira.todos.infrastructure.data;

    opens io.vieira.todos.infrastructure.data.jpa to spring.core, org.hibernate.orm.core;
}