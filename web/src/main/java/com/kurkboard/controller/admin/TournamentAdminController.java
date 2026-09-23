package com.kurkboard.controller.admin;

import com.kurkboard.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tournamentPanel")
public class TournamentAdminController {

    private final TournamentService tournamentService;

    public TournamentAdminController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String panel(Model model) {
        model.addAttribute(
                "tournaments",
                tournamentService.findAll()
        );

        return "admin/tournamentPanel";
    }

    @GetMapping("/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model) {

        tournamentService.findById(id)
                .ifPresent(tournament ->
                        model.addAttribute("tournament", tournament)
                );

        return "admin/tournamentPanel";
    }
}