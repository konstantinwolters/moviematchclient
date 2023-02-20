package com.example.moviematchweb.service;

import java.util.List;

import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;

public interface LikeService {

    void addLike(User currentUser, Integer tmdbMovieId);

    List<Integer> getMovieLikeStats(Session currentSession, Integer tmdbMovieId);

    boolean matched(Session currentSession, Integer tmdbMovieId);

}
