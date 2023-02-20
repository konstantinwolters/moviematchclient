package com.example.moviematchweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.moviematchweb.dto.MovieDTO;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.exception.customExceptions.NoResultsException;
import com.example.moviematchweb.proxy.MMProxy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

        private final MMProxy mmProxy;

        public MovieDTO getMovieForVoting(
                        Session currentSession,
                        Integer page,
                        Integer movieCount) {

                List<MovieDTO> movies = mmProxy.getMovies(
                                currentSession.getId(),
                                page,
                                currentSession.getGenres().stream()
                                                .map(Genre -> String.valueOf(Genre.getId()))
                                                .collect(Collectors.joining("|")));

                if (movies.size() == 0) {
                        throw new NoResultsException(true, "There are no results for your search parameters.");
                }

                try {
                        movies.get(movieCount);
                } catch (IndexOutOfBoundsException e) {
                        throw new NoResultsException(false, "There are no more results for your search parameters.");
                }

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
