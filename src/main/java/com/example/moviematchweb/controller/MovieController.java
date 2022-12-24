package com.example.moviematchweb.controller;

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

            // TMDB api limits results to 20 per request (=page) TODO: In eigene Methode
            // auslagern und bereits gelikte Movies ausblenden
            if (movieCount >= 19) {
                movieCount = 0;
                page += 1;
            } else {
                movieCount += 1;
            }

            MovieDTO movie = movieService.getMovieForVoting(apiKey, currentSession, page, movieCount);
            movie.setPoster_path(posterPath + movie.getPoster_path());
            model.addAttribute("matched", matched);
            model.addAttribute("likeStats", likeService.getMovieLikeStats(currentSession, movie.getId()));
            model.addAttribute("page", page);
            model.addAttribute("movieCount", movieCount);
            model.addAttribute("movie", movie);
            return "votings";

        } else {
            return "error";
        }
    }
}
