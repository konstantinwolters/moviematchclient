package com.example.moviematchweb.service;

import java.util.List;

import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;

import jakarta.servlet.http.HttpSession;

public interface SessionService {

    User getUserFromSessionByUserId(Session session, Long UserId);

    Session addNewSession(HttpSession httpSession, List<Integer> selectedGenres, List<String> names);

}
