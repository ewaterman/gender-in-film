package com.ewaterman.genderinfilm;

import com.ewaterman.genderinfilm.characters.*;
import com.ewaterman.genderinfilm.characters.Character;
import com.ewaterman.genderinfilm.common.BooleanAnswer;
import com.ewaterman.genderinfilm.movies.Movie;
import com.ewaterman.genderinfilm.movies.MovieQuestion;
import com.ewaterman.genderinfilm.movies.MovieQuestionType;
import com.ewaterman.genderinfilm.movies.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import java.util.List;

/**
 * Loads some test data for easy debugging.
 * TODO: Remove this file once we have a proper importer.
 */
@Component
public class TempDataLoader implements CommandLineRunner {

    private final MovieService movieService;
    private final CharacterService characterService;

    public TempDataLoader(MovieService movieService, CharacterService characterService) {
        this.movieService = movieService;
        this.characterService = characterService;
    }

    @Override
    public void run(String... args) {
        if (true) {
            return;
        }

        // If we already have the test movie, we're done.
        List<Movie> movies = movieService.findByNameIgnoreCaseContaining("Test Movie");
        if (!ListUtils.isEmpty(movies)) {
            return;
        }

        Movie movie = Movie.builder()
                .name("Test Movie")
                .tmdbId("1")
                .build();

        movie.setQuestions(List.of(
                MovieQuestion.builder()
                        .question(MovieQuestionType.TRANS_WRITER)
                        .answer(BooleanAnswer.NO)
                        .movie(movie)
                        .details("hi hello hurray!")
                        .build()
        ));

        movieService.save(movie);

        Character character = Character.builder()
                .name("Test Character")
                .type(CharacterType.TRANS)
                .build();

        characterService.save(character);

        // TODO: This isn't working yet. Create a method: saveMovieCharacter() that fails if the character doesn't
        // exist. CRUD APIs ARE ALWAYS BETTER!
        MovieCharacter movieCharacter = MovieCharacter.builder()
                .movie(movie)
                .character(character)
                .build();

        movieCharacter.setQuestions(
                List.of(
                        CharacterQuestion.builder()
                                .question(CharacterQuestionType.ALIVE_AT_END)
                                .answer(BooleanAnswer.YES)
                                .build(),
                        CharacterQuestion.builder()
                                .question(CharacterQuestionType.TRANS_ACTOR)
                                .answer(BooleanAnswer.YES)
                                .build(),
                        CharacterQuestion.builder()
                                .question(CharacterQuestionType.IN_MULTIPLE_SCENES)
                                .answer(BooleanAnswer.YES)
                                .build(),
                        CharacterQuestion.builder()
                                .question(CharacterQuestionType.VILLAINOUS)
                                .answer(BooleanAnswer.YES)
                                .build(),
                        CharacterQuestion.builder()
                                .question(CharacterQuestionType.IS_NAMED)
                                .answer(BooleanAnswer.YES)
                                .build(),
                        CharacterQuestion.builder()
                                .question(CharacterQuestionType.IS_GENDER_JOKE)
                                .answer(BooleanAnswer.YES)
                                .build()
                )
        );
    }
}
