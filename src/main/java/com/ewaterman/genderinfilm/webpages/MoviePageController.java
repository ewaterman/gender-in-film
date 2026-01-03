package com.ewaterman.genderinfilm.webpages;

import com.ewaterman.genderinfilm.common.BooleanAnswer;
import com.ewaterman.genderinfilm.external.tmdb.TmdbApiWrapper;
import com.ewaterman.genderinfilm.movies.Movie;
import com.ewaterman.genderinfilm.movies.MovieService;

import info.movito.themoviedbapi.model.movies.MovieDb;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MoviePageController {

    private final MovieService movieService;
    private final TmdbApiWrapper tmdbApiWrapper;

    public MoviePageController(final MovieService movieService,
                               final TmdbApiWrapper tmdbApiWrapper) {
        this.movieService = movieService;
        this.tmdbApiWrapper = tmdbApiWrapper;
    }

    @GetMapping("/{id}")
    String indexPage(@PathVariable Long id, Model model) {

        // Get our movie info first
        Movie movie = movieService.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + id));
        model.addAttribute("ourMovie", movie);

        // Then use the TMDB id to fetch TMDB data
        MovieDb tmdbMovie = tmdbApiWrapper.getMovieDetails(Integer.parseInt(movie.getTmdbId()));
        if (tmdbMovie == null) {
            throw new IllegalArgumentException("TMDB Movie not found with id: " + movie.getTmdbId());
        }
        model.addAttribute("tmdbMovie", tmdbMovie);

        return "movie";
    }

    /**
     * Like indexPage but for when the movie might not actually exist (and so we look up by TMDB instead).
     * Should only need to be used when searching for movies. Otherwise, indexPage is preferred.
     */
    @GetMapping("/tmdb/{tmdbId}")
    String tmdbIndexPage(@PathVariable Integer tmdbId, Model model) {

        // Get TMDB data first
        MovieDb tmdbMovie = tmdbApiWrapper.getMovieDetails(tmdbId);
        if (tmdbMovie == null) {
            throw new IllegalArgumentException("TMDB Movie not found with id: " + tmdbId);
        }
        model.addAttribute("tmdbMovie", tmdbMovie);

        // Then use the TMDB id to fetch our movie data
        Optional<Movie> movie = movieService.findByTmdbId(tmdbId);
        model.addAttribute("ourMovie", movie.orElse(null));

        return "movie";
    }

    @GetMapping("/create")
    String createPage(@RequestParam Integer tmdbId, Model model) {
        MovieDb tmdbMovie = tmdbApiWrapper.getMovieDetails(tmdbId);
        Movie movie = movieService.initMovie(tmdbMovie);

        model.addAttribute("movie", movie);
        model.addAttribute("tmdbMovie", tmdbMovie);
        model.addAttribute("answerTypes", BooleanAnswer.values());
        return "movie-edit";
    }

    @GetMapping("/{id}/update")
    String updatePage(@PathVariable long id, Model model) {
        Movie movie = movieService.findByIdAndPopulate(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + id));
        MovieDb tmdbMovie = tmdbApiWrapper.getMovieDetails(Integer.parseInt(movie.getTmdbId()));

        model.addAttribute("movie", movie);
        model.addAttribute("tmdbMovie", tmdbMovie);
        model.addAttribute("answerTypes", BooleanAnswer.values());
        return "movie-edit";
    }

    @PostMapping("/save")
    public String saveOperation(@ModelAttribute("movie") Movie movie) {
        long id = movieService.save(movie).getId();
        return "redirect:/movies/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteOperation(@PathVariable long id) {
        movieService.delete(id);
        return "redirect:/";
    }
}
