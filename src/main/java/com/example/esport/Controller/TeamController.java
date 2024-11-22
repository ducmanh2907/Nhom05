package com.example.esport.Controller;

import com.example.esport.Service.TeamService;
import com.example.esport.model.Team;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    // Display a list of all products
    @GetMapping
    public String showTeamList(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "teams/team-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("team", new Team());

        return "teams/add-team";
    }
    @GetMapping("/points")
    public String showTeamsSortedByPoints(Model model) {
        List<Team> teams = teamService.getAllTeamsSortedByPoints();
        model.addAttribute("teams", teams);
        return "teams/points";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addTeam(@Valid Team team, @RequestParam("imagea") MultipartFile imagea,   BindingResult result) {
        if (result.hasErrors()) {
            return "teams/add-team";
        }
        teamService.addTeam(team, imagea);
        return "redirect:/teams";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Team team = teamService.getTeamById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("team", team);

        return "teams/update-team";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable Long id, @Valid Team team, @RequestParam("imagea") MultipartFile imagea,
                                BindingResult result) {
        if (result.hasErrors()) {
            team.setId(id); // set id to keep it in the form in case of errorsreturn "/products/update-product";
        }
        teamService.updateTeam(team, imagea);
        return "redirect:/teams";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "redirect:/teams";
    }
}
