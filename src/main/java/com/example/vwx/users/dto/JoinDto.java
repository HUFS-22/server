package com.example.vwx.users.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDto {

    @ApiModelProperty(example = "김ㅇㅇ")
    private String userName;
    @ApiModelProperty(example = "kim")
    private String loginId;
    @ApiModelProperty(example = "password")
    private String password;
    @ApiModelProperty(example = "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/06/29/25514ae1-adb7-43ca-87c4-8c6cab1d41c0.jpg")
    private String coverImageUrl;
    @ApiModelProperty(example = "https://i.namu.wiki/i/5WnTJSXMP_rH0S8H1jEjgH-r4fVNHZCs3gS7iLrLN2eDtp18yHCNIrRL1-tlmT_zOwcjye6GZ6uRVql5_ZEZ8AvuYi79NamY2l7H6su-MIwZV5GWAOMWw_iARWxikf3PtqTmOxJ8JH2kv4aQuHuWHQ.webp")
    private String profileImageUrl;
    @ApiModelProperty(example = "프리랜서")
    private String belong;
    @ApiModelProperty(example = "배우")
    private String job;
}
