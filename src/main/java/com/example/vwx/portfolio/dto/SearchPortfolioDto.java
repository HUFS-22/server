package com.example.vwx.portfolio.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPortfolioDto {
    private Long portfolioId;
    private String title;
    private String coverImageUrl;
    private String userName;
    private String profileImageUrl;

}
