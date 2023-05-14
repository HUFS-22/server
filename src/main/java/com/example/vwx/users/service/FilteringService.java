package com.example.vwx.users.service;

import com.example.vwx.common.domain.BaseException;
import com.example.vwx.users.domain.Filtering;
import com.example.vwx.users.repository.FilteringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FilteringService {

    private final FilteringRepository filteringRepository;

    public Optional<Filtering> findByKeyword(String keyword) {
        return filteringRepository.findByKeyword(keyword);
    }

    public Filtering save(String keyword){
        return filteringRepository.save(
                Filtering.builder()
                        .keyword(keyword)
                        .build());
    }
}
