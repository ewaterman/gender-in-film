package com.ewaterman.genderinfilm.movies;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ewaterman.genderinfilm.common.base.ComponentController;
import com.ewaterman.genderinfilm.external.tmdb.TmdbApiWrapper;

@Controller
@RequestMapping("/components/movies")
public class MovieComponentController extends ComponentController {

    private final MovieService movieService;
    private final TmdbApiWrapper tmdbApiWrapper;

    public MovieComponentController(final MovieService movieService,
                                    final TmdbApiWrapper tmdbApiWrapper) {
        this.movieService = movieService;
        this.tmdbApiWrapper = tmdbApiWrapper;
    }

    /**
     * Fetch a list of movies whose titles contains the given text and renders them as HTML for search results.
     * Does not rely on TMDB. The returned movies are only those that exist within our system.
     *
     * No longer used. Replaced with findDetailsByMovieTitle.
     */
    @GetMapping("/search")
    public String findByMovieTitle(@RequestParam String movieTitle, Model model) {
        List<Movie> movies = movieService.findByNameIgnoreCaseContaining(movieTitle);
        model.addAttribute("searchedMovies", movies);

        return "components/movie-search-result";
    }

    /**
     * Fetch a list of movies whose titles contains the given text and renders them as HTML for a detailed
     * search results, containing additional info such as the year of release, and the movie poster.
     *
     * We return all existing movies that match the search results (as defined by TMDB), not just the movies
     * that we already have data for.
     */
    @GetMapping("/details/search")
    public String findDetailsByMovieTitle(@RequestParam String movieTitle, Model model) {
        // We first look the movies up in TMDB then check if we have any data associated with the matching TMDB ids
        Map<Integer, info.movito.themoviedbapi.model.core.Movie> tmdbMovies = tmdbApiWrapper.searchForMovieMappedByTmdbId(movieTitle);
        Map<Integer, Movie> moviesByTmdbId = movieService.findAllByTmdbIdsMappedByTmdbId(tmdbMovies.keySet());

        model.addAttribute("tmdbMoviesByTmdbId", tmdbMovies);
        model.addAttribute("ourMoviesByTmdbId", moviesByTmdbId);

        return "components/movie-details-search-result";
    }
}
