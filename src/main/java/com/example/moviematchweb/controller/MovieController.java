package com.example.moviematchweb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moviematchweb.dto.MovieDTO;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;
import com.example.moviematchweb.service.MovieService;
import com.example.moviematchweb.service.LikeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final LikeService likeService;

    @Value("${tmdb.api.key}")
    String apiKey;

    String posterPath = "https://image.tmdb.org/t/p/w400";

    @GetMapping("/voting")
    public String movieResults(
            @RequestParam Integer page,
            @RequestParam Integer movieCount,
            @RequestParam Integer vote,
            @RequestParam(required = false) Integer tmdbMovieId,
            Model model,
            HttpSession httpSession) {

        if (httpSession.getAttribute("currentSession") != null) {
            Session currentSession = Session.class.cast(httpSession.getAttribute("currentSession"));
            User currentUser = User.class.cast(httpSession.getAttribute("currentUser"));
            Boolean matched = false;

            if (vote == 1) {
                likeService.addLike(currentUser, tmdbMovieId);
                matched = likeService.matched(currentSession, tmdbMovieId);
                if (matched) {
                    MovieDTO movie = movieService.getMovieForVoting(apiKey, currentSession, page, movieCount);
                    model.addAttribute("likedMoviePosterPath", posterPath + movie.getPoster_path());
                }
            }

            Map<String, Integer> nextMoviePageAndCount = movieService.nextMoviePageAndCount(currentSession, page,
                    movieCount);

            MovieDTO movie = movieService.getMovieForVoting(apiKey, currentSession, nextMoviePageAndCount.get("page"),
                    nextMoviePageAndCount.get("movieCount"));
            movie.setPoster_path(posterPath + movie.getPoster_path());
            model.addAttribute("matched", matched);
            model.addAttribute("likeStats", likeService.getMovieLikeStats(currentSession, movie.getId()));
            model.addAttribute("page", nextMoviePageAndCount.get("page"));
            model.addAttribute("movieCount", nextMoviePageAndCount.get("movieCount"));
            model.addAttribute("movie", movie);
            return "votings";

        } else {
            return "error";
        }
    }
}
