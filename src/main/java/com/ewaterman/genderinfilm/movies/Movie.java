package com.ewaterman.genderinfilm.movies;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

import com.ewaterman.genderinfilm.characters.MovieCharacter;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @OneToMany(mappedBy="movie", cascade=CascadeType.ALL)
    private List<MovieCharacter> movieCharacters = new ArrayList<>();

    @OneToMany(mappedBy="movie", cascade=CascadeType.ALL)
    private List<MovieQuestion> questions = new ArrayList<>();

    /**
     * A copy of the movie title from TMDB. We store name directly on the movie so that we can support
     * searching without needing to depend so heavily on TMDB. It also allows us to easily restrict our search
     * to only movies that we have data for.
     */
    @Column(name="name", nullable=false)
    private String name;

    /**
     * Maps movies to their metadata in The Movie Database.
     */
    @Column(name="tmdb_id", nullable=false)
    private String tmdbId;

    public String toString() {
        return name;
    }
}
