package com.ewaterman.genderinfilm.movies;

import com.ewaterman.genderinfilm.common.BooleanAnswer;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a single question pertaining to a movie.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;

    @Enumerated(EnumType.STRING)
    @Column(name="question", nullable=false)
    private MovieQuestionType question;

    @Enumerated(EnumType.STRING)
    @Column(name="answer", nullable=false)
    private BooleanAnswer answer;

    /**
     * In case the answer needs a longer explanation.
     */
    @Column(name="details")
    private String details;

    public String toString() {
        return question + ": " + answer;
    }
}
