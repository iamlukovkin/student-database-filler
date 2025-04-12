# RSREU postgresql filling

A java program for generating data for database tables.

## Installation

Firstly configure your credentials in [config](./src/main/resources/hibernate.cfg.xml)

```xml
<property name="connection.driver_class">org.postgresql.Driver</property>
<property name="connection.url">jdbc:postgresql://localhost:5432/your_database</property>
<property name="connection.username">your_username</property>
<property name="connection.password">EnterStrongPassword</property>
<property name="connection.pool_size">1</property>
<property name="dialect">org.hibernate.dialect.PostgreSQLDialect</property>
```

After filling your credentials build a project

```bash
mvn clean package
```

## Using

By success installation you can run executable jar 

```bash
java -jar ./target/database_filler-1.0-SNAPSHOT.jar
```

