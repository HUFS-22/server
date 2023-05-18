package com.example.vwx.portfolio.dto;

import com.example.vwx.users.dto.SearchArtistDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
    private List<SearchArtistDto> artistList;
    private List<SearchPortfolioDto> portfolioList;
}
