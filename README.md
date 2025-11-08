# Gender In Film

A SpringBoot webapp for analysing transgender and gender non-conforming representation in film.
Kind of like a transgender [Bechdel Test](https://bechdeltest.com/).


## Usage Instructions
Navigate to (insert url here) and search for the movie that you'd like to know about!


## TODO
- spring security to allow people to log in and add new movies/characters
- create dockerfile for generating jar so that Java isn't a deployment requirement anymore
- create indexes on all tables


## Developer Info

### App Spec
- SpringBoot 3.3.2 with Java 22 and Thymeleaf
- Postgres database
- Liquibase for database migrations


### Setup
- Install Java 22.0.1
    - I use [SDKMAN!](https://sdkman.io/): `sdk install java 22.0.1-open` then `sdk use java 22.0.1-open` to apply it to only the current session or `sdk default java 22.0.1-open` to make it permanent.


### Running The App Via Docker
1. [Install Docker](https://www.docker.com/)
2. `./mvnw spring-boot:run`

Internally, this runs `docker-compose up` which will start the application and postgres in Docker containers
(`app` and `db` respectively).


### Creating A New Release
1. Update the version tag in the pom file with the new version.
2. Bring down the app: `docker-compose down`
3. Generate a new jar: `./mvnw package`
4. Generate a new tagged Docker image: `docker build . -t genderinfilm:0.0.1`

You can also run the app via the JAR file with `java -jar target/genderinfilm-0.0.1.jar`


### Debugging

To debug the postgres container: `docker exec -it db psql -U postgres -d genderinfilm`


### Troubleshooting

- If you make changes to the Dockerfile, remember to run `docker-compose build --no-cache` before `docker-compose up`
- If there are any dangling containers, run `docker-compose down` to clean them up.


### Contributing

This is just a hobby project, and so I can't guarantee the fastest of response times, but I'll try my best to
get to any questions. Pull requests are always welcome!
