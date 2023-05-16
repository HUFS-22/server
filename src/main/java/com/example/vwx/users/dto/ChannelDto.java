package com.example.vwx.users.dto;

import com.example.vwx.portfolio.dto.ChannelPortfolioDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChannelDto {
    private String userName;
    private String coverImageUrl;
    private String profileImageUrl;
    private String belong;
    private String job;
    private List<ChannelPortfolioDto> portfolioList;
}
