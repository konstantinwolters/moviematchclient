package com.example.moviematchweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.moviematchweb.dto.Session;
import com.example.moviematchweb.dto.User;
import com.example.moviematchweb.proxy.MMProxy;
import com.example.moviematchweb.service.GenreService;
import com.example.moviematchweb.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final SessionService sessionService;
    private final GenreService genreService;
    private final MMProxy mmProxy;

    @GetMapping(value = { "/", "/{sessionUuid}/{userId}" })
    public String blabla(
            @PathVariable(required = false) String sessionUuid,
            @PathVariable(required = false) Long userId,
            Model model,
            HttpSession httpSession) {

        if (sessionUuid != null || userId != null) {

            Session currentSession = mmProxy.retrieveSession(sessionUuid, userId);
            User currentUser = sessionService.getUserFromSessionByUserId(currentSession,
                    userId);

            httpSession.setAttribute("currentSession", currentSession);
            httpSession.setAttribute("currentUser", currentUser);
            model.addAttribute("currentSession", currentSession);
            model.addAttribute("currentUser", currentUser);
        }
        return "index";
    }

    @GetMapping("/newsession")
    public String newSession(Model model) {

        model.addAttribute("genres", genreService.getGenres());
        return "newsession";
    }

    @PostMapping("/newsession/yoursession")
    public String yourSession(
            @RequestParam(required = false) List<String> selectedGenres,
            @RequestParam List<String> names,
            Model model,
            HttpSession httpSession,
            HttpServletRequest request) {

        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("currentSession",
                sessionService.addNewSession(httpSession, selectedGenres, names));
        return "summary";
    }

    @GetMapping("/testarea")
    public String testArea(Model model) {

        model.addAttribute("flag", true);
        return "testarea";
    }

    @GetMapping("/enjoy")
    public String enjoy(Model model) {

        return "enjoy";
    }
}
