package com.ewaterman.genderinfilm.webpages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ewaterman.genderinfilm.characters.CharacterType;
import com.ewaterman.genderinfilm.characters.MovieCharacter;
import com.ewaterman.genderinfilm.characters.MovieCharacterService;
import com.ewaterman.genderinfilm.common.BooleanAnswer;

@Controller
@RequestMapping("/movies/{movieId}/characters")
public class MovieCharacterPageController {

    private final MovieCharacterService movieCharacterService;

    public MovieCharacterPageController(final MovieCharacterService movieCharacterService) {
        this.movieCharacterService = movieCharacterService;
    }

    @GetMapping("/{movieCharacterId}")
    String indexPage(@PathVariable long movieId, @PathVariable Long movieCharacterId, Model model) {
        MovieCharacter movieCharacter = movieCharacterService.findById(movieCharacterId).orElseThrow(() ->
                new IllegalArgumentException("Movie character not found with id: " + movieCharacterId));

        model.addAttribute("movieCharacter", movieCharacter);

        return "movie-character";
    }

    @GetMapping("/create")
    String createPage(@PathVariable long movieId, Model model) {
        MovieCharacter movieCharacter = movieCharacterService.initMovieCharacter(movieId);

        model.addAttribute("movieCharacter", movieCharacter);
        model.addAttribute("movieId", movieId);
        model.addAttribute("answerTypes", BooleanAnswer.values());
        model.addAttribute("characterType", CharacterType.values());
        return "movie-character-edit";
    }

    @GetMapping("/{movieCharacterId}/update")
    String updatePage(@PathVariable long movieId, @PathVariable long movieCharacterId, Model model) {
        MovieCharacter movieCharacter = movieCharacterService.findByIdAndPopulate(movieCharacterId).orElseThrow(() ->
                new IllegalArgumentException("Movie character not found with id: " + movieCharacterId));

        model.addAttribute("movieCharacter", movieCharacter);
        model.addAttribute("movieId", movieId);
        model.addAttribute("answerTypes", BooleanAnswer.values());
        model.addAttribute("characterType", CharacterType.values());
        return "movie-character-edit";
    }

    @PostMapping("/save")
    public String saveOperation(@PathVariable long movieId,
                                @ModelAttribute("movieCharacter") MovieCharacter movieCharacter) {
        long id = movieCharacterService.save(movieCharacter).getId();
        return "redirect:/movies/" + movieId + "/characters/" + id;
    }

    @DeleteMapping("/{movieCharacterId}")
    public String deleteOperation(@PathVariable long movieId, @PathVariable long movieCharacterId) {
        movieCharacterService.delete(movieCharacterId);
        return "redirect:/movies/" + movieId;
    }
}
