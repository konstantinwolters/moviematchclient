package com.example.moviematchweb.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO {
    private Integer page;
    private List<MovieDTO> results;
    private Integer total_results;
    private Integer total_pages;

}
