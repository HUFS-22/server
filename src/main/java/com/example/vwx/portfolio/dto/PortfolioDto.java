package com.example.vwx.portfolio.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioDto {

    @ApiModelProperty(example = "제목")
    private String title;
    @ApiModelProperty(example = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/06/29/25514ae1-adb7-43ca-87c4-8c6cab1d41c0.jpg")
    private String coverImageUrl;
    @ApiModelProperty(example = "내용")
    private String content;
    @ApiModelProperty(example = "['영화','드라마']")
    List<String> categories;
    @ApiModelProperty(example = "['청순','여자배우','로코']")
    List<String> hashTags;
}
