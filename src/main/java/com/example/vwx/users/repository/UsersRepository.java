package com.example.vwx.users.repository;

import com.example.vwx.users.domain.Users;
import com.example.vwx.users.dto.SearchArtistDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u INNER JOIN FilterMapping m ON m.users = u INNER JOIN Filtering f ON m.filtering = f WHERE f.keyword = :word")
    List<Users> findByKeyword(@Param("word") String word);

    @Query(value = "SELECT DISTINCT new com.example.vwx.users.dto.SearchArtistDto(u.userName, u.belong, u.job, u.profileImageUrl)" +
            "FROM Users u INNER JOIN FilterMapping m ON m.users = u " +
            "INNER JOIN Filtering f ON m.filtering = f")
    List<SearchArtistDto> findMainByKeyword(@Param("word") String word, Pageable pageable);
}
