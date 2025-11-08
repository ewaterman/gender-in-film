package com.ewaterman.genderinfilm.characters;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MovieCharacterService {

    private final MovieCharacterRepository movieCharacterRepository;

    public MovieCharacterService(MovieCharacterRepository movieCharacterRepository) {
        this.movieCharacterRepository = movieCharacterRepository;
    }

    public Optional<MovieCharacter> findById(Long id) {
        return movieCharacterRepository.findById(id);
    }

    public MovieCharacter save(MovieCharacter character) {
        return movieCharacterRepository.save(character);
    }

    public MovieCharacter update(Long id, MovieCharacter character) {
        if (!movieCharacterRepository.existsById(id)) {
            throw new IllegalArgumentException("Movie character with id " + character + " does not exist");
        }

        return movieCharacterRepository.save(character);
    }

    public void delete(Long id) {
        movieCharacterRepository.deleteById(id);
    }
}
