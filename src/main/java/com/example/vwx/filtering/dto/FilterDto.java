package com.example.vwx.filtering.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDto {
    @ApiModelProperty(example = "['수영', '축구', '배우', '연극배우', '흑발', '여자', '일본어', '영어']")
    List<String> keywords;
}
