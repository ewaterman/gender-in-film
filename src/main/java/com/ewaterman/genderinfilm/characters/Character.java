package com.ewaterman.genderinfilm.characters;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a character. Characters will have one entry in MovieCharacter for each movie that they're in.
 * This allows us to account for characters that appear in multiple movies (sequels, spin-offs, ...).
 * As such, a "character" can also be thought of as shorthand for a "franchise character".
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @OneToMany(mappedBy="character", cascade=CascadeType.ALL)
    private List<MovieCharacter> movieCharacters = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable=false)
    private CharacterType type;

    @Column(name="name")
    private String name;

    public String toString() {
        return name;
    }
}
