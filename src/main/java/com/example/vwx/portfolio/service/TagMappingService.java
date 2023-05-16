package com.example.vwx.portfolio.service;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.domain.Tag;
import com.example.vwx.portfolio.domain.TagMapping;
import com.example.vwx.portfolio.repository.TagMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TagMappingService {

    private final TagService tagService;
    private final TagMappingRepository tagMappingRepository;

    public void save(List<String> hashtags, Portfolio portfolio) {
        if(hashtags.size() == 0) return;
        hashtags.stream()
                .map(hashtag ->
                        tagService.findByHashtag(hashtag)
                                .orElseGet(() -> tagService.save(hashtag)))
                .forEach(hashtag -> mapHashTagToPortfolio(hashtag, portfolio));
    }

    private Long mapHashTagToPortfolio(Tag hashtag, Portfolio portfolio) {
        TagMapping tagMapping = new TagMapping(portfolio, hashtag);
        return tagMappingRepository.save(tagMapping).getId();
    }
}
