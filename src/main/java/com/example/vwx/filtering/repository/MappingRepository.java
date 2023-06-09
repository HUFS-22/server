package com.example.vwx.filtering.repository;


import com.example.vwx.filtering.domain.FilterMapping;
import com.example.vwx.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MappingRepository extends JpaRepository<FilterMapping, Long> {

    @Query("SELECT f.keyword " +
            "FROM FilterMapping m " +
            "INNER JOIN Filtering f ON f = m.filtering " +
            "WHERE m.users = :user")
    List<String> findAllByUsers(@Param("user") Users user);

    @Query("SELECT m FROM FilterMapping m WHERE m.users = :user")
    List<FilterMapping> findFilterMappingsByUsers(@Param("user") Users users);

    @Modifying
    @Query("DELETE FROM FilterMapping m WHERE m.users = :user")
    void deleteAllByUsers(@Param("user") Users users);
}
