package com.ewaterman.genderinfilm.characters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ewaterman.genderinfilm.common.base.ApiController;

@RestController
@RequestMapping("/api/movie-characters")
public class MovieCharacterApiController extends ApiController {

    private final MovieCharacterService movieCharacterService;

    public MovieCharacterApiController(MovieCharacterService movieCharacterService) {
        this.movieCharacterService = movieCharacterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> findById(@PathVariable Long id) {
        return ResponseEntity.of(movieCharacterService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @PostMapping
    public ResponseEntity<MovieCharacter> save(@RequestBody MovieCharacter character) {
        return new ResponseEntity<>(movieCharacterService.save(character), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @PutMapping("/{id}")
    public ResponseEntity<MovieCharacter> update(@PathVariable Long id, @RequestBody MovieCharacter character) {
        return ResponseEntity.ok(movieCharacterService.update(id, character));
    }

    @PreAuthorize("hasRole('ROLE_EDITOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieCharacterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
