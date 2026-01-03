package com.ewaterman.genderinfilm.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ewaterman.genderinfilm.common.base.ApiController;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController extends ApiController {

    private final MovieService movieService;

    public MovieApiController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.update(id, movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
