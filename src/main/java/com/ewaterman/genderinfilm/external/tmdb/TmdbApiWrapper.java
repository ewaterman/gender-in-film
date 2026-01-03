package com.ewaterman.genderinfilm.external.tmdb;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.MovieAppendToResponse;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A wrapper component around <a href="https://github.com/c-eg/themoviedbapi">TMDB API</a>.
 * See <a href="https://developer.themoviedb.org/docs/getting-started">TMDB Developer Guide</a> for details.
 */
@Component
public class TmdbApiWrapper {

    private final TmdbApi tmdbApi;

    TmdbApiWrapper(@Value("${tmdb.api.key:}") String tmdbApiKey) {
        tmdbApi = new TmdbApi(tmdbApiKey);
    }

    /**
     * Search TMDB my movie title for a list of matching movies.
     * <a href="https://developer.themoviedb.org/reference/search-movie">API Details</a>
     */
    public List<Movie> searchForMovie(String movieTitle) {
        if (StringUtils.isBlank(movieTitle)) {
            return null;
        }

        try {
            return tmdbApi.getSearch().searchMovie(
                    movieTitle,
                    false,  // No adult films. Can reconsider this later.
                    "en-US",
                    null,
                    1,  // We only care about the first page of results.
                    null,
                    null).getResults();
        } catch (TmdbException e) {
            return null;
        }
    }

    /**
     * Search TMDB my movie title for a list of matching movies, returning them as a map keyed on TMDB id.
     * <a href="https://developer.themoviedb.org/reference/search-movie">API Details</a>
     */
    public Map<Integer, Movie> searchForMovieMappedByTmdbId(String movieTitle) {
        if (StringUtils.isBlank(movieTitle)) {
            return null;
        }

        return searchForMovie(movieTitle).stream().collect(Collectors.toMap(Movie::getId, Function.identity()));
    }

    /**
     * Get details for a movie from TMDB by the TMDB id of the movie.
     * <a href="https://developer.themoviedb.org/reference/movie-details">API Details</a>
     */
    public MovieDb getMovieDetails(int movieId) {
        try {
            return tmdbApi.getMovies().getDetails(movieId, "en-US", MovieAppendToResponse.IMAGES);
        } catch (TmdbException e) {
            return null;
        }
    }
}
