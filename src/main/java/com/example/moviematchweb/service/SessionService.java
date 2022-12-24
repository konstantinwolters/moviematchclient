package com.example.moviematchweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.moviematchweb.dto.NewSessionRequestDTO;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;
import com.example.moviematchweb.proxy.MMProxy;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final MMProxy mmProxy;

    public User getUserFromSessionByUserId(Session session, Long UserId) {

        User currentUser = session.getUsers().stream()
                .filter(user -> user.getId().equals(UserId))
                .findFirst().get();

        return currentUser;
    }

    public Session addNewSession(HttpSession httpSession, List<Integer> selectedGenres, List<String> names) {
        Session newSession;

        if (selectedGenres == null) {
            newSession = mmProxy.newSession(
                    NewSessionRequestDTO.builder()
                            .genres(null)
                            .names(names)
                            .build());
        } else {
            newSession = mmProxy.newSession(
                    NewSessionRequestDTO.builder()
                            .genres(selectedGenres)
                            .names(names)
                            .build());
        }

        httpSession.setAttribute("movieSession", newSession);
        httpSession.setAttribute("currentUser", newSession.getUsers().get(0).getId());

        return newSession;
    }

}
