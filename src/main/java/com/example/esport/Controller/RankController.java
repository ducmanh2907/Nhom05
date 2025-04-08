package com.example.esport.Controller;

import com.example.esport.model.Ranking;
import com.example.esport.Service.TeamService;
import com.example.esport.Service.RankService;
import com.example.esport.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/rankings")
public class RankController {
    @Autowired
    private RankService rankService;
    @Autowired
    private TeamService teamService; // Đảm bảo bạn đã injectCategoryService
    // Display a list of all products
    @GetMapping
    public String showRankList(Model model) {
        model.addAttribute("rankings", rankService.getAllRankings());

        return "rankings/ranking-list";
    }

    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ranking", new Ranking());
        model.addAttribute("teams", teamService.getAllTeams()); //Load categories
        return "rankings/add-ranking";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@Valid Ranking ranking, BindingResult result) {
        if (result.hasErrors()) {
            return "rankings/add-ranking";
        }
        rankService.addRanking(ranking);
        return "redirect:/rankings";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ranking ranking = rankService.getRankingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("ranking", ranking);
        model.addAttribute("teams", teamService.getAllTeams()); //Load categories
        return "rankings/update-ranking";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateRanking(@PathVariable Long id, @Valid Ranking ranking,
                                BindingResult result) {
        if (result.hasErrors()) {
            ranking.setId(id); // set id to keep it in the form in case of errorsreturn "/products/update-product";
        }
        rankService.updateRanking(ranking);
        return "redirect:/rankings";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteRanking(@PathVariable Long id) {
        rankService.deleteRankingById(id);
        return "redirect:/rankings";
    }
}
