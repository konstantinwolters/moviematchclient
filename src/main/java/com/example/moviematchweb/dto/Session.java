package com.example.moviematchweb.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private String id;
    private LocalDate lastAccess;
    private List<User> users;
    private List<Genre> genres;

}
