package com.example.padelversus.team;


import com.example.padelversus.team.display.TeamsPageDisplayInfo;
import com.example.padelversus.tournament.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamsController {

    @Autowired
    TeamService teamService;

    @GetMapping("/")
    public String teams(Model model, Pageable page) {
        Page<Team> pages = teamService.getPages(page);
        List<String> pageTeamNames = teamService.getPageTeamNamesforTeamsController(page);
        List<Tournament> tournamentsList = teamService.getAllTournaments();

        TeamsPageDisplayInfo teamsPageDisplayInfo = new TeamsPageDisplayInfo(tournamentsList, pageTeamNames);
        model.addAttribute("Next", pages.getNumber() + 1);
        model.addAttribute("Last", pages.getNumber() - 1);
        model.addAttribute("showNext", !pages.isLast());
        model.addAttribute("showPrev", !pages.isFirst());
        model.addAttribute("allTournamentsInfo", teamsPageDisplayInfo.getTournamentDisplays());

        return "teams";
    }
}
