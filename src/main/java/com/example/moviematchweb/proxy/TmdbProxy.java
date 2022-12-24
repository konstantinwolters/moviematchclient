package com.example.moviematchweb.proxy;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.moviematchweb.dto.GenreDTO;
import com.example.moviematchweb.dto.ResultDTO;

@FeignClient(name = "TmdbProxy", url = "${name.service.url}")
public interface TmdbProxy {

        @GetMapping("/discover/movie?api_key={apiKey}" +
                        "&language=en-US" +
                        "&sort_by=popularity.desc" +
                        "&vote_count.gte=200" +
                        "&vote_average.gte=5" +
                        // "&primary_release_year=" + "{year}" +
                        "&with_genres=" + "{genre}" +
                        "&page={page}")
        ResultDTO getMovies(@PathVariable String apiKey,
                        // @PathVariable String year,
                        @PathVariable String genre,
                        @PathVariable Integer page);

        @GetMapping("/genre/movie/list?api_key={apiKey}&language=en-US")
        Map<String, List<GenreDTO>> getGenres(@PathVariable String apiKey);

}
