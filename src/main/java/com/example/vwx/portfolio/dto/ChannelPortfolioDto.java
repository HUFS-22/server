package com.example.vwx.portfolio.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChannelPortfolioDto {
    private String title;
    private String coverImageUrl;
}
