package com.example.vwx.users.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllArtistDto {
    List<ArtistDto> artistList;
}
