package com.example.vwx.portfolio.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioResponseDto {
    private Long id;
    private String title;
    private String coverImageUrl;
    private String content;
    List<String> categories;
    List<String> hashTags;
}
