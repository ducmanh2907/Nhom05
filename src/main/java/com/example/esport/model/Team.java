package com.example.esport.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.example.esport.model.Ticker;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    private String name;
    private String logoss;


    public String getLogoss() {
        return logoss;
    }

    public void setLogoss(String logoss) {
        this.logoss = logoss;
    }

    private String country;

    private String description;

    // Quan hệ 1-N với Ranking
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Ranking> rankings;

}

