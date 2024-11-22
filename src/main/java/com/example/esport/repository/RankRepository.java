package com.example.esport.repository;

import com.example.esport.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RankRepository extends JpaRepository<Ranking, Long> {
}