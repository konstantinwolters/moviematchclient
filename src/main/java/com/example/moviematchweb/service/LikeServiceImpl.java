package com.example.moviematchweb.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.moviematchweb.dto.Like;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;
import com.example.moviematchweb.proxy.MMProxy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final MMProxy mmProxy;

    public void addLike(User currentUser, Integer tmdbMovieId) {
        if (!currentUser.getLikes().stream()
                .anyMatch(like -> like.getTmdbMovieId() == tmdbMovieId)) {
            mmProxy.addLike(Like.builder()
                    .user(currentUser)
                    .tmdbMovieId(tmdbMovieId)
                    .build());
        }
    }

    public List<Integer> getMovieLikeStats(Session currentSession, Integer tmdbMovieId) {
        Integer liked = mmProxy.getMatchedLikeCount(currentSession.getUuid(), tmdbMovieId);
        Integer notLiked = currentSession.getUsers().size() - liked;

        return Arrays.asList(liked, notLiked);
    }

    public boolean matched(Session currentSession, Integer tmdbMovieId) {
        List<Integer> likeStats = getMovieLikeStats(currentSession, tmdbMovieId);

        return likeStats.get(1).equals(0);
    }

}
