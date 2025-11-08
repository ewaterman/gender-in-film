package com.ewaterman.genderinfilm.characters;

import com.ewaterman.genderinfilm.common.BooleanAnswer;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a single question pertaining to a character in a specific movie.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="movie_character_id", nullable=false)
    private MovieCharacter movieCharacter;

    @Enumerated(EnumType.STRING)
    @Column(name="question", nullable=false)
    private CharacterQuestionType question;

    @Enumerated(EnumType.STRING)
    @Column(name="answer", nullable=false)
    private BooleanAnswer answer;

    /**
     * In case the answer needs a longer explanation.
     */
    @Column(name="details")
    private String details;

}
