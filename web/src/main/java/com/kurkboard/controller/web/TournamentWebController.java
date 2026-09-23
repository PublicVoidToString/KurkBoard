package com.kurkboard.controller.web;

import com.kurkboard.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tournament")
public class TournamentWebController {

    private final TournamentService tournamentService;

    public TournamentWebController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute(
                "tournaments",
                tournamentService.findAll()
        );

        return "tournament/list";
    }

    @GetMapping("/{id:\\d+}")
    public String tournament(
            @PathVariable Integer id,
            Model model) {

        tournamentService.findById(id)
                .ifPresent(tournament ->
                        model.addAttribute("tournament", tournament)
                );

        return "tournament/details";
    }
}