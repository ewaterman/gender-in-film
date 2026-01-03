package com.ewaterman.genderinfilm.movies;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findByTmdbId(String tmdbId);

    List<Movie> findAllByTmdbIdIn(List<String> tmdbIds);

    List<Movie> findByNameIgnoreCaseContaining(String name);
}
