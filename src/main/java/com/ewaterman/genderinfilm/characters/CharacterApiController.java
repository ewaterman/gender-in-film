package com.ewaterman.genderinfilm.characters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ewaterman.genderinfilm.common.base.ApiController;

@RestController
@RequestMapping("/api/characters")
public class CharacterApiController extends ApiController {

    private final CharacterService characterService;

    public CharacterApiController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> findById(@PathVariable Long id) {
        return ResponseEntity.of(characterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Character> save(@RequestBody Character character) {
        return new ResponseEntity<>(characterService.save(character), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody Character character) {
        return ResponseEntity.ok(characterService.update(id, character));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
