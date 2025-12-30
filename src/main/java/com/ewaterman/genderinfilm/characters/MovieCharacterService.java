package com.ewaterman.genderinfilm.characters;

import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.ewaterman.genderinfilm.movies.Movie;
import com.ewaterman.genderinfilm.movies.MovieRepository;

@Service
public class MovieCharacterService {

    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;
    private final MovieCharacterRepository movieCharacterRepository;

    public MovieCharacterService(final CharacterRepository characterRepository,
                                 final MovieRepository movieRepository,
                                 final MovieCharacterRepository movieCharacterRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
        this.movieCharacterRepository = movieCharacterRepository;
    }

    public Optional<MovieCharacter> findById(Long id) {
        return movieCharacterRepository.findById(id);
    }

    /**
     * Fetch a movie character by id and populate it with any questions that it doesn't already have.
     * This makes updating the movie character much simpler for the frontend.
     */
    public Optional<MovieCharacter> findByIdAndPopulate(Long id) {
        return findById(id).map(this::populateMovieCharacterWithMissingQuestions);
    }

    /**
     * Initialize a new movie character by populating a full list of (empty) questions. This makes creating the movie
     * character much simpler for the frontend.
     */
    public MovieCharacter initMovieCharacter(long movieId) {
        MovieCharacter movieCharacter = new MovieCharacter();

        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalArgumentException("Movie not found with id: " + movieId));
        movieCharacter.setMovie(movie);

        // Movie characters are currently one-to-one with characters so we also create the character here. This is
        // fine for now but eventually we'll want to create a movie character based on an existing character.
        Character character = new Character();
        movieCharacter.setCharacter(character);

        List<CharacterQuestion> questions = movieCharacter.getQuestions();
        for (CharacterQuestionType questionType : CharacterQuestionType.values()) {
            questions.add(CharacterQuestion.builder()
                    .movieCharacter(movieCharacter)
                    .question(questionType)
                    .build());
        }
        return movieCharacter;
    }

    /**
     * For every question, if the movie character doesn't have the question, add an empty version of it.
     */
    private MovieCharacter populateMovieCharacterWithMissingQuestions(MovieCharacter movieCharacter) {
        List<CharacterQuestion> questions = movieCharacter.getQuestions();
        for (CharacterQuestionType questionType : CharacterQuestionType.values()) {
            // If the question already exists for the movie character, use it as is.
            if (questions.stream().anyMatch(q -> questionType.equals(q.getQuestion()))) {
                continue;
            }

            // Otherwise add the question without an answer.
            questions.add(CharacterQuestion.builder()
                    .movieCharacter(movieCharacter)
                    .question(questionType)
                    .build());
        }
        return movieCharacter;
    }

    public MovieCharacter save(MovieCharacter character) {
        // We create the character and the movie character within the same operation. In the future the character
        // will already exist at this point, but for now simply create the character first, then the movie character.
        Character franchiseCharacter = character.getCharacter();
        if (StringUtils.isBlank(franchiseCharacter.getName())) {
            franchiseCharacter.setName(null);  // We don't want blank names, only null if they're unnamed.
        }
        characterRepository.save(character.getCharacter());

        // We need to set the inverse side of the question relationship manually before saving.
        for (CharacterQuestion characterQuestion : character.getQuestions()) {
            characterQuestion.setMovieCharacter(character);
        }
        return movieCharacterRepository.save(character);
    }

    public MovieCharacter update(Long id, MovieCharacter character) {
        if (!movieCharacterRepository.existsById(id)) {
            throw new IllegalArgumentException("Movie character with id " + character + " does not exist");
        }

        return movieCharacterRepository.save(character);
    }

    public void delete(Long id) {
        movieCharacterRepository.deleteById(id);
    }
}
