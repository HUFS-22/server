package com.example.vwx.users.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDto {

    private Long id;
    private String userName;
    private String belong;
    private String job;
    private String profileImageUrl;
    List<String> filtering;
}
