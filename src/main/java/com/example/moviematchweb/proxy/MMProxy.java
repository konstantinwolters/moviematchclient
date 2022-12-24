package com.example.moviematchweb.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.moviematchweb.dto.Like;
import com.example.moviematchweb.dto.NewSessionRequestDTO;
import com.example.moviematchweb.dto.Session;

@FeignClient(name = "MMProxy", url = "localhost:8080")
public interface MMProxy {

    @PostMapping("/session")
    Session newSession(@RequestBody NewSessionRequestDTO NewSessionRequest);

    @GetMapping("/session/{sessionUuid}/{userId}")
    Session retrieveSession(@PathVariable String sessionUuid, @PathVariable Long userId);

    @PostMapping("/like")
    void addLike(@RequestBody Like like);

    @GetMapping("/like/matchedLikeCount/{sessionUuid}/{tmdbMovieId}")
    Integer getMatchedLikeCount(@PathVariable String sessionUuid, @PathVariable Integer tmdbMovieId);

}
