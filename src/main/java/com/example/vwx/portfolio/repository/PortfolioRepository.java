package com.example.vwx.portfolio.repository;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.dto.SearchPortfolioDto;
import com.example.vwx.users.domain.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("SELECT p FROM Portfolio p WHERE p.users = :user")
    List<Portfolio> findByUsers(@Param("user") Users users);

    @Query("SELECT DISTINCT new com.example.vwx.portfolio.dto.SearchPortfolioDto (p.id, p.title, p.coverImageUrl, u.userName, u.profileImageUrl) FROM Portfolio p INNER JOIN Users u ON p.users = u " +
            "INNER JOIN TagMapping m ON m.portfolio = p " +
            "INNER JOIN Tag t ON m.tag = t " +
            "WHERE p.title Like CONCAT('%',:word,'%') OR t.hashtag Like CONCAT('%',:word,'%')")
    List<SearchPortfolioDto> findByKeyword(@Param("word") String word);

    @Query(value = "SELECT DISTINCT new com.example.vwx.portfolio.dto.SearchPortfolioDto (p.id, p.title, p.coverImageUrl, u.userName, u.profileImageUrl) " +
            "FROM Portfolio p INNER JOIN Users u ON p.users = u " +
            "INNER JOIN TagMapping m ON m.portfolio = p " +
            "INNER JOIN Tag t ON m.tag = t " +
            "WHERE p.title Like CONCAT('%',:word,'%') OR t.hashtag Like CONCAT('%',:word,'%') ")
    List<SearchPortfolioDto> findMainByKeyword(@Param("word") String word, Pageable pageable);
}
