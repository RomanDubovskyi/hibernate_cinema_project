package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/all")
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(this::transferMovieToDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/add")
    public Movie addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        Movie newMovie = new Movie();
        newMovie.setDescription(movieRequestDto.getDescription());
        newMovie.setTitle(movieRequestDto.getTitle());
        return movieService.add(newMovie);
    }

    private MovieResponseDto transferMovieToDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }
}
