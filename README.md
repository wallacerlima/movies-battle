# Movies Battle API

Rest API for movies battle game. Developed with the Java programming language and the Spring Boot Framework.

## Technologies 

- [Spring Boot](https://spring.io/projects/spring-boot) - Development Framework for the Java Language.

- [Lombok](https://projectlombok.org/) - Java library focused on productivity and boilerplate code reduction that, through annotations added to our code, teach the compiler (maven or gradle) during the compilation process to create Java code.

- [JUnit5](https://junit.org/junit5/) - Framework that facilitates the creation and maintenance of code for test automation with results presentation.

- [Mockito](https://site.mockito.org/) - Open source test framework for Java released under the MIT license. The framework allows for the creation of dual test objects in automated unit tests for the purpose of test-driven development or behavior-driven development.

- [H2 Database](https://www.postgresql.org/download/) - Disk-based or in-memory database.

- [Hibernate](https://hibernate.org/) - Framework for object-relational mapping written in Java language. (ORM)

- [JPA](https://hibernate.org/orm/) - Java specification that dictates how ORM Frameworks should be implemented.

- [Docker](https://www.docker.com/) - Open source platform that facilitates the creation and administration of isolated environments. It makes it possible to package an application or environment inside a container, making it portable to any other host that has Docker installed.

- [OpenAPI](https://swagger.io/) - Specification for machine-readable interface files for describing, producing, consuming, and visualizing RESTful web services.


## To compile and run the project you need to have installed:

 - [Maven](https://maven.apache.org/) - Build automation tool primarily used in Java projects.
 - [Docker](https://docs.docker.com/get-docker/) - Running container applications.
 
## Installation

 - Clone the project: `$git clone https://github.com/wallacerlima/movies-battle-api.git`
 - After completing the download of the project, access the directory: `$cd movies-battle-api/`
 - At the root of the project run the following command: `chmod 777 docker-run.sh` and then `$./docker-run.sh`
 
## Documentation

You can access OpenAPI documentation through this URL: http://localhost:8080/swagger-ui/index.html

## API description
### Authentication
- All API endpoints require a valid authentication from a player. This API uses a basic authentication with user and password. 
- Test credentials: (User: wallacelima, Password: 123456), (User: jaumzin, Password: 123456)

### Match
- Start a new match - GET: http://localhost:8080/v1/match/start
- Finish a match - GET: http://localhost:8080/v1/match/finish

### Round
- Get a new round - GET: http://localhost:8080/v1/round
- Answer a round question with movieId - POST: http://localhost:8080/v1/round/answer

    ```json
    {
       "movieId": "string"
    }
    ```
### Ranking
- Get the overall ranking - GET: http://localhost:8080/v1/ranking