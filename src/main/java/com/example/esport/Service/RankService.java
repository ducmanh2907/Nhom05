package com.example.esport.Service;

import com.example.esport.model.Ranking;
import com.example.esport.repository.RankRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RankService {
    private final RankRepository rankRepository;
    // Retrieve all products from the database
    public List<Ranking> getAllRankings() {
        return rankRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Ranking> getRankingById(Long id) {
        return rankRepository.findById(id);
    }
    public Optional<Ranking> findById(Long id) {
        return rankRepository.findById(id);
    }
    // Add a new product to the database
    public Ranking addRanking(Ranking ranking)
    {return rankRepository.save(ranking);

    }

    // Update an existing product
    public Ranking updateRanking(@NotNull Ranking ranking) {
        Ranking existingRanking = rankRepository.findById(ranking.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        ranking.getId() + " does not exist."));
        existingRanking.setPosition(ranking.getPosition());
        existingRanking.setPoints(ranking.getPoints());

        existingRanking.setSeason(ranking.getSeason());



        return rankRepository.save(existingRanking);
    }



    // Delete a product by its id
    public void deleteRankingById(Long id) {
        if (!rankRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        rankRepository.deleteById(id);
    }
}