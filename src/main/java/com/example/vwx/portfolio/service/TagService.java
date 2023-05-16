package com.example.vwx.portfolio.service;

import com.example.vwx.portfolio.domain.Tag;
import com.example.vwx.portfolio.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public Optional<Tag> findByHashtag(String hashtag) {
        return tagRepository.findByHashtag(hashtag);
    }

    public Tag save(String hashtag) {
        return tagRepository.save(
                Tag.builder()
                        .hashtag(hashtag)
                        .build());
    }
}
