package com.example.moviematchweb.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.moviematchweb.dto.Like;
import com.example.moviematchweb.dto.NewSessionRequestDTO;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.MovieDTO;
import com.example.moviematchweb.dto.Genre;

@FeignClient(name = "MMProxy", url = "localhost:8080")
public interface MMProxy {

    @PostMapping("/session")
    Session newSession(@RequestBody NewSessionRequestDTO NewSessionRequest);

    @GetMapping("/session/{sessionId}/{userId}")
    Session retrieveSession(@PathVariable String sessionId, @PathVariable Long userId);

    @PostMapping("/like")
    void addLike(@RequestBody Like like);

    @GetMapping("/like/matchedLikeCount/{sessionId}/{tmdbMovieId}")
    Integer getMatchedLikeCount(@PathVariable String sessionId, @PathVariable Integer tmdbMovieId);

    @GetMapping("/genre/genres")
    List<Genre> getGenres();

    @GetMapping("/movie/{sessionId}?page={page}&genres={genres}")
    List<MovieDTO> getMovies(@PathVariable String sessionId, @PathVariable Integer page, @PathVariable String genres);

}
