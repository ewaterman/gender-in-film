package com.ewaterman.genderinfilm.characters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
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
