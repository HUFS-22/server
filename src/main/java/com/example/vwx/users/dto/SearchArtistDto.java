package com.example.vwx.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchArtistDto {
    private Long userId;
    private String userName;
    private String belong;
    private String job;
    private String profileImageUrl;
}
