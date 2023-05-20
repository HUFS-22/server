package com.example.vwx.portfolio.service;

import com.example.vwx.common.domain.BaseException;
import com.example.vwx.portfolio.dto.SearchPortfolioDto;
import com.example.vwx.portfolio.domain.Portfolio;
import com.example.vwx.portfolio.dto.PortfolioDto;
import com.example.vwx.portfolio.dto.SearchDto;
import com.example.vwx.portfolio.repository.PortfolioRepository;
import com.example.vwx.portfolio.repository.TagMappingRepository;
import com.example.vwx.portfolio.repository.WorkCategoryRepository;
import com.example.vwx.users.domain.Users;
import com.example.vwx.users.dto.SearchArtistDto;
import com.example.vwx.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.vwx.common.domain.BaseResponseStatus.NOT_FOUND_PORTFOLIO;
import static com.example.vwx.common.domain.BaseResponseStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final UsersRepository usersRepository;
    private final WorkCategoryRepository workCategoryRepository;
    private final TagMappingRepository tagMappingRepository;
    private final TagMappingService tagMappingService;
    private final WorkCategoryService workCategoryService;

    public String uploadPortfolio(PortfolioDto portfolioDto, Long userId) throws BaseException {
        Users users = usersRepository.findById(userId).orElseThrow(()-> new BaseException(UNAUTHORIZED));
        Portfolio portfolio = Portfolio.builder()
                .title(portfolioDto.getTitle())
                .coverImageUrl(portfolioDto.getCoverImageUrl())
                .content(portfolioDto.getContent())
                .users(users)
                .build();
        portfolioRepository.save(portfolio);
        List<String> categories = portfolioDto.getCategories();
        List<String> hashTags = portfolioDto.getHashTags();
        workCategoryService.save(categories, portfolio);
        tagMappingService.save(hashTags, portfolio);
        return "포트폴리오를 등록했습니다.";
    }

    public PortfolioDto getPortfolio(Long portfolioId) throws BaseException {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(() -> new BaseException(NOT_FOUND_PORTFOLIO));
        List<String> workCategory = workCategoryRepository.findCategoriesByPortfolio(portfolio);
        List<String> hashtags = tagMappingRepository.findTagsByPortfolio(portfolio);

        return PortfolioDto.builder()
                .title(portfolio.getTitle())
                .content(portfolio.getContent())
                .coverImageUrl(portfolio.getCoverImageUrl())
                .categories(workCategory)
                .hashTags(hashtags)
                .build();
    }

    public SearchDto searchAll(String word) throws BaseException {
        List<SearchArtistDto> usersList = usersRepository.findMainByKeyword(word, PageRequest.of(0,5)); // 5개 추출
        List<SearchPortfolioDto> portfolio = portfolioRepository.findMainByKeyword(word, PageRequest.of(0,5)); //5개 추출
        return SearchDto.builder()
                .artistList(usersList.stream().collect(Collectors.toList()))
                .portfolioList(portfolio.stream().collect(Collectors.toList()))
                .build();
    }

    public List<SearchPortfolioDto> searchPortfolio(String word) throws BaseException {
        List<SearchPortfolioDto> portfolioList = portfolioRepository.findByKeyword(word);
        List<SearchPortfolioDto> portfolioAllList = new ArrayList<>();
        for (SearchPortfolioDto portfolio : portfolioList) {
            SearchPortfolioDto dto = SearchPortfolioDto.builder()
                    .portfolioId(portfolio.getPortfolioId())
                    .title(portfolio.getTitle())
                    .coverImageUrl(portfolio.getCoverImageUrl())
                    .userName(portfolio.getUserName())
                    .profileImageUrl(portfolio.getProfileImageUrl())
                    .build();
            portfolioAllList.add(dto);
        }
        return portfolioAllList;
    }
}
