package com.ewaterman.genderinfilm.external.tmdb;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.appendtoresponse.MovieAppendToResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A wrapper component around <a href="https://github.com/c-eg/themoviedbapi">TMDB API</a>.
 * See <a href="https://developer.themoviedb.org/docs/getting-started">TMDB Developer Guide</a> for details.
 */
@Component
public class TmdbApiWrapper {

    private final TmdbApi tmdbApi;

    @Value("${tmdb.api.key:}")
    String tmdbApiKey;

    TmdbApiWrapper() {
        tmdbApi = new TmdbApi(tmdbApiKey);
    }

    public MovieDb getMovieDetails(int movieId) {
        try {
            return tmdbApi.getMovies().getDetails(movieId, "en-US", MovieAppendToResponse.IMAGES);
        } catch (TmdbException e) {
            return null;
        }
    }
}
