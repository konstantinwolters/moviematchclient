package com.example.moviematchweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.moviematchweb.dto.NewSessionRequestDTO;
import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;
import com.example.moviematchweb.proxy.MMProxy;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final MMProxy mmProxy;

    public User getUserFromSessionByUserId(Session session, Long UserId) {

        User currentUser = session.getUsers().stream()
                .filter(user -> user.getId().equals(UserId))
                .findFirst().get();

        return currentUser;
    }

    public Session addNewSession(HttpSession httpSession, List<String> selectedGenres, List<String> names) {
        Session newSession;

        newSession = mmProxy.newSession(
                NewSessionRequestDTO.builder()
                        .genres(selectedGenres)
                        .names(names)
                        .build());

        httpSession.setAttribute("currentSession", newSession);
        httpSession.setAttribute("currentUser", newSession.getUsers().get(0).getId());

        return newSession;
    }

}
