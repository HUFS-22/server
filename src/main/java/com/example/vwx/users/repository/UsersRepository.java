package com.example.vwx.users.repository;

import com.example.vwx.filtering.dto.FilterDto;
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

    
    // 수정 필요
    @Query(value = "SELECT DISTINCT new com.example.vwx.users.dto.SearchArtistDto(u.userName, u.belong, u.job, u.profileImageUrl) " +
            "FROM Users u INNER JOIN FilterMapping m ON m.users = u " +
            "INNER JOIN Filtering f ON m.filtering = f " +
            "WHERE f.keyword Like CONCAT('%',:word,'%') AND f.keyword = :keyword")
    List<SearchArtistDto> findByKeyword(@Param("word") String word, @Param("keyword") String keyword);

    @Query(value = "SELECT DISTINCT new com.example.vwx.users.dto.SearchArtistDto(u.userName, u.belong, u.job, u.profileImageUrl)" +
            "FROM Users u INNER JOIN FilterMapping m ON m.users = u " +
            "INNER JOIN Filtering f ON m.filtering = f " +
            "WHERE f.keyword Like CONCAT('%',:word,'%')")
    List<SearchArtistDto> findMainByKeyword(@Param("word") String word, Pageable pageable);

    @Query(value = "SELECT DISTINCT new com.example.vwx.users.dto.SearchArtistDto(u.userName, u.belong, u.job, u.profileImageUrl)" +
            "FROM Users u INNER JOIN FilterMapping m ON m.users = u " +
            "INNER JOIN Filtering f ON m.filtering = f " +
            "WHERE f.keyword Like CONCAT('%',:word,'%')")
    List<SearchArtistDto> findSearchByKeyword(@Param("word") String word);
}
