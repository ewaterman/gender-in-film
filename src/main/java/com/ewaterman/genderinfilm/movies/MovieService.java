package com.ewaterman.genderinfilm.movies;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findByNameIgnoreCaseContaining(String name) {
        return movieRepository.findByNameIgnoreCaseContaining(name);
    }

    /**
     * Fetch a movie by id and populate it with any questions that it doesn't already have. This makes updating
     * the movie much simpler for the frontend.
     */
    public Optional<Movie> findByIdAndPopulate(Long id) {
        return findById(id).map(this::populateMovieWithMissingQuestions);
    }

    /**
     * Initialize a new movie by populating a full list of (empty) questions. This makes creating the movie
     * much simpler for the frontend.
     */
    public Movie initMovie() {
        Movie movie = new Movie();
        List<MovieQuestion> questions = movie.getQuestions();
        for (MovieQuestionType questionType : MovieQuestionType.values()) {
            questions.add(MovieQuestion.builder()
                    .movie(movie)
                    .question(questionType)
                    .build());
        }
        return movie;
    }

    /**
     * For every question, if the movie doesn't have the question, add an empty version of it to the movie.
     */
    private Movie populateMovieWithMissingQuestions(Movie movie) {
        List<MovieQuestion> questions = movie.getQuestions();
        for (MovieQuestionType questionType : MovieQuestionType.values()) {
            // If the question already exists for the movie, use it as is.
            if (questions.stream().anyMatch(q -> questionType.equals(q.getQuestion()))) {
                continue;
            }

            // Otherwise add the question without an answer.
            questions.add(MovieQuestion.builder()
                    .movie(movie)
                    .question(questionType)
                    .build());
        }
        return movie;
    }

    public Optional<Movie> findByTmdbId(String id) {
        return movieRepository.findByTmdbId(id);
    }

    public Movie save(Movie movie) {
        // We need to set the inverse side of the question relationship manually before saving.
        for (MovieQuestion movieQuestion : movie.getQuestions()) {
            movieQuestion.setMovie(movie);
        }
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Movie movie) {
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException("Movie with id " + id + " does not exist");
        }

        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
