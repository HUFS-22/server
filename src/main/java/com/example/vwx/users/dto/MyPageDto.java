package com.example.vwx.users.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageDto {
    @ApiModelProperty(example = "김ㅇㅇ")
    private String userName;
    @ApiModelProperty(example = "https://i.namu.wiki/i/5WnTJSXMP_rH0S8H1jEjgH-r4fVNHZCs3gS7iLrLN2eDtp18yHCNIrRL1-tlmT_zOwcjye6GZ6uRVql5_ZEZ8AvuYi79NamY2l7H6su-MIwZV5GWAOMWw_iARWxikf3PtqTmOxJ8JH2kv4aQuHuWHQ.webp")
    private String profileImageUrl;
}
