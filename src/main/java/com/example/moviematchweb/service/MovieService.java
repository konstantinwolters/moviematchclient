package com.example.moviematchweb.service;

import java.util.Map;

import com.example.moviematchweb.dto.MovieDTO;
import com.example.moviematchweb.dto.Session;

public interface MovieService {

        MovieDTO getMovieForVoting(Session currentSession, Integer page, Integer movieCount);

        Map<String, Integer> nextMoviePageAndCount(Session currSession, Integer page, Integer movieCount);
}
