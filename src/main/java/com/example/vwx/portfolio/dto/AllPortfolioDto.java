package com.example.vwx.portfolio.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllPortfolioDto {

    List<PortfolioResponseDto> portfolioDtoList;
}
