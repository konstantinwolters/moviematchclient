package com.example.moviematchweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.moviematchweb.dto.Genre;
import com.example.moviematchweb.proxy.MMProxy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final MMProxy mmProxy;

    @Override
    public List<Genre> getGenres() {

        return mmProxy.getGenres();
    }

}
