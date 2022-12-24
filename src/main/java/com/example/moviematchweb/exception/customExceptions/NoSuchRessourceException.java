package com.example.moviematchweb.exception.customExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoSuchRessourceException extends RuntimeException {
    private String message;
}
