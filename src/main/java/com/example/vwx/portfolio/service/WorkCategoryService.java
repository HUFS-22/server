package com.example.vwx.portfolio.service;

import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.domain.WorkCategory;
import com.example.vwx.portfolio.repository.WorkCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkCategoryService {

    private final WorkCategoryRepository workCategoryRepository;

    public void save(List<String> categories, Portfolio portfolio) {
        if(categories.size() == 0) return;
        categories.stream()
                .forEach(category -> workCategoryRepository.save(new WorkCategory(category, portfolio)));
    }
}
