package com.example.moviematchweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.moviematchweb.dto.Genre;
import com.example.moviematchweb.dto.MovieDTO;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.proxy.TmdbProxy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

        private final TmdbProxy tmdbProxy;

        public MovieDTO getMovieForVoting(String apiKey,
                        Session currentSession,
                        Integer page,
                        Integer movieCount) {

                List<MovieDTO> movies = tmdbProxy.getMovies(
                                apiKey,
                                currentSession.getGenres().stream()
                                                .map(Genre::getTmdbGenreId)
                                                .toList()
                                                .toString()
                                                .replace("[", "")
                                                .replace("]", ""),
                                page)
                                .getResults();

                return movies.get(movieCount);
        }

}
