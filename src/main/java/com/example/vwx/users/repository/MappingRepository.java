package com.example.vwx.users.repository;


import com.example.vwx.users.domain.Mapping;
import com.example.vwx.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MappingRepository extends JpaRepository<Mapping, Long> {

    @Query("SELECT m FROM Mapping m where m.users = :user")
    List<Mapping> findAllByUsers(@Param("user") Users user);
}
