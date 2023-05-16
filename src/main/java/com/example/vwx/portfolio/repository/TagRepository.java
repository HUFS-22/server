package com.example.vwx.portfolio.repository;

import com.example.vwx.portfolio.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByHashtag(@Param("hashtag") String hashtag);

}
