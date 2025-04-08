package com.example.esport.Service;

import com.example.esport.model.Team;
import com.example.esport.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;
    // Retrieve all products from the database
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }
    // Add a new product to the database
    public Team addTeam(Team team, MultipartFile imagea) {
        try {
            if (imagea != null && !imagea.isEmpty()) {
                String logoss = saveImageStatic(imagea);
                team.setLogoss(logoss);
            }

            return teamRepository.save(team);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    public List<Team> getAllTeamsSortedByPoints() {
        List<Team> teams = teamRepository.findAll();

<<<<<<< HEAD
        // Sắp xếp các đội dựa trên điểm số
        teams.sort(Comparator.comparingInt(team -> {
            if (team.getRankings() != null && !team.getRankings().isEmpty()) {
                return team.getRankings().get(0).getPoints();
            }
            // Nếu không có Ranking, mặc định điểm là 0
            return 0;
        }));
        return teams;
    }

=======
        // Sort teams based on points
        teams.sort(Comparator.comparingInt(team -> team.getRankings().get(0).getPoints()));
        return teams;
    }
>>>>>>> b2be986 (jonggun)
    // Update an existing product
    public Team updateTeam(@NotNull Team team, MultipartFile imagea) {
        Team existingTeam = teamRepository.findById(team.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        team.getId() + " does not exist."));
        existingTeam.setName(team.getName());
        existingTeam.setCountry(team.getCountry());

        existingTeam.setDescription(team.getDescription());


        try {
            if (imagea != null && !imagea.isEmpty()) {
                String logoss = saveImageStatic(imagea);
                existingTeam.setLogoss(logoss);
            }


        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return teamRepository.save(existingTeam);
    }


    private String saveImageStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
    // Delete a product by its id
    public void deleteTeamById(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        teamRepository.deleteById(id);
    }
<<<<<<< HEAD
    public List<Team> searchMatches(String search) {
        return teamRepository.findByNameContainingIgnoreCase(search); // Tìm kiếm trận đấu theo tên
    }
=======
>>>>>>> b2be986 (jonggun)
}