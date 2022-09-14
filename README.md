# Java-Swing-DB-Tool

As the name suggests, this is a simple Java Swing MySQL Database Application.
It Contains Methods for Creating, Filling, Reading and Deleting a Database.

For simplicity, I added an optional docker-compose File, which sets up MySQL and PHPmyAdmin.

## Requirements

[JDK](https://www.oracle.com/java/technologies/downloads/)

[Java-Editor](https://javaeditor.org/doku.php?id=en:installation)

[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
(Put the mysql-connector-java-*.jar file in the project root folder for simplicity.)

[Docker](https://www.docker.com/)

## Setup

#### MySQL Connector/J

1. Open Java-Editor and go to Window > Configuration > Java > Interpreter.
2. Under Classpath-User select the mysql-connector-java-*.jar file.

#### Docker

1. Make sure Docker is running.
2. Open CMD or Terminal inside the project
3. Enter: ```docker-compose up```

(To shut down the Container, enter: ```docker-compose down```)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
