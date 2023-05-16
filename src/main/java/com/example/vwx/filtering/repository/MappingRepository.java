package com.example.vwx.filtering.repository;


import com.example.vwx.filtering.domain.Mapping;
import com.example.vwx.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MappingRepository extends JpaRepository<Mapping, Long> {

    @Query("SELECT f.keyword FROM Mapping m INNER JOIN Filtering f ON f = m.filtering where m.users = :user")
    List<String> findAllByUsers(@Param("user") Users user);
}
