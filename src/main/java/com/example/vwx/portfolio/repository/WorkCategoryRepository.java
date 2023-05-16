package com.example.vwx.portfolio.repository;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.domain.WorkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkCategoryRepository extends JpaRepository<WorkCategory, Long> {

    @Query("SELECT w.category FROM WorkCategory w WHERE w.portfolio = :portfolio")
    List<String> findCategoriesByPortfolio(@Param("portfolio") Portfolio portfolio);
}
