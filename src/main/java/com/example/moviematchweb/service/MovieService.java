package com.example.moviematchweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
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

        @Value("${tmdb.api.key}")
        String apiKey;

        // TODO: Exceptionhandling, wenn keine Ergebnisse oder wenn Array zu Ende ist
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

        public Map<String, Integer> nextMoviePageAndCount(Session currSession, Integer page, Integer movieCount) {

                Map<String, Integer> nextMovie = new HashMap<>();

                // TMDB api limits results to 20 per request (=page)
                // TODO: bereits gelikte Movies ausblenden
                if (movieCount >= 19) {
                        nextMovie.put("movieCount", 0);
                        nextMovie.put("page", page += 1);
                } else {
                        nextMovie.put("movieCount", movieCount += 1);
                        nextMovie.put("page", page);
                        movieCount += 1;
                }
                return nextMovie;
        }
}
