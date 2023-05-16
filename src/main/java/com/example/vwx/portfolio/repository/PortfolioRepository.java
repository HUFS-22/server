package com.example.vwx.portfolio.repository;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("SELECT p FROM Portfolio p WHERE p.users = :user")
    List<Portfolio> findByUsers(@Param("user") Users users);
}
