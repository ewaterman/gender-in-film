package com.ewaterman.genderinfilm.characters;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ewaterman.genderinfilm.movies.Movie;

/**
 * Represents a character in a single film. If a character exists in multiple films they will have multiple entries
 * in this table, one per film.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @OneToOne
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name="character_id", nullable=false)
    private Character character;

    @OneToMany(mappedBy="movieCharacter", cascade=CascadeType.ALL)
    private List<CharacterQuestion> questions = new ArrayList<>();
}
