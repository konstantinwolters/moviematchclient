package com.example.moviematchweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Like {
    private Long id;
    private User user;
    private Integer tmdbMovieId;
}
