package com.example.vwx.users.repository;

import com.example.vwx.users.domain.Filtering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilteringRepository extends JpaRepository<Filtering, Long> {

    Optional<Filtering> findByKeyword(@Param("keyword") String keyword);
}
