# Gender In Film

A SpringBoot webapp for analysing transgender and gender non-conforming representation in film.
Kind of like a transgender [Bechdel Test](https://bechdeltest.com/).


## Usage Instructions
Navigate to (insert url here) and search for the movie that you'd like to know about!


## TODO
- Priority 1 (Get the basic framework working.)
  - spring security to allow people to log in and add new movies/characters
  - add roles and make all delete calls admin only

- Priority 2 (All the remaining essentials. After this point, we could launch.)
  - list all movies page (paginated) when hit enter in searchbar (also have "show more options" at bottom of search results)
  - add tmdb data on movie create (poster mainly, but can also do budget, rating, genre, star rating, description)
  - show tmdb data on movie details page
  - show tmdb data on movie search (can we get mini images so it's faster?)
  - style everything ṕroperly

- Priority 3 (Nice to haves. Not needed for launch.)
  - homepage reports
  - l10n support (via message.properties files)
  - create dockerfile for generating jar so that Java isn't a deployment requirement anymore
  - create indexes on all tables
  - make the movie/character questions dynamic (ie move them to the db)
  - movie franchise
    - a movie can only be in a single franchise. When adding a movie, can set a franchise for it.
    - a "character" is essentially a "franchise character"
    - when creating a movie character. if a movie is in a franchise, it shows all characters of that franchise in a search bar, and you can select a franchise character to map to the movie character that you're creating (or create a new one if they DNE)
    - if a character appears in more than one franchise, they're treated as totally independent (Santa is in so many movies, but they're all re-imaginings of the character)
    - pages for CRUD operations on a franchise character (independent of any film)      /franchises/{id}/characters
    - change movies URI to be   /franchises/{id}/movies ???
    - change movie character URI to be /franchises/{id}/movies/{id}/characters    (what to do if movie has no franchise?)
    - pages for CRUD ops on a character /franchises/{id}/characters/1    GET returns all movies the franchise character is in

## Developer Info

### App Spec
- SpringBoot 3.3.2 with Java 22 and Thymeleaf
- Postgres database
- Liquibase for database migrations


### Setup
- Install Java 22.0.1
    - I use [SDKMAN!](https://sdkman.io/): `sdk install java 22.0.1-open` then `sdk use java 22.0.1-open` to apply it to only the current session or `sdk default java 22.0.1-open` to make it permanent.


### Running The App
1. [Install Docker](https://www.docker.com/)
2. `./mvnw spring-boot:run`

Internally, this runs `docker-compose up` which will start the application and postgres in Docker containers
(`app` and `db` respectively).

You can also run the app via your IDE (such as Intellij) by creating a run config that points to `com.ewaterman.genderinfilm.Application`.


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
