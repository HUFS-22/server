package com.example.vwx.portfolio.repository;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.domain.TagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMappingRepository extends JpaRepository<TagMapping, Long> {

    @Query("SELECT t.hashtag FROM TagMapping m INNER JOIN Tag t ON t = m.tag WHERE m.portfolio = :portfolio")
    List<String> findTagsByPortfolio(@Param("portfolio") Portfolio portfolio);
}
