package com.ewaterman.genderinfilm.characters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-characters")
public class MovieCharacterController {

    private final MovieCharacterService movieCharacterService;

    public MovieCharacterController(MovieCharacterService movieCharacterService) {
        this.movieCharacterService = movieCharacterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> findById(@PathVariable Long id) {
        return ResponseEntity.of(movieCharacterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovieCharacter> save(@RequestBody MovieCharacter character) {
        return new ResponseEntity<>(movieCharacterService.save(character), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieCharacter> update(@PathVariable Long id, @RequestBody MovieCharacter character) {
        return ResponseEntity.ok(movieCharacterService.update(id, character));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieCharacterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
