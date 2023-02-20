package com.example.moviematchweb.exception.customExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoResultsException extends RuntimeException {
    private boolean isNewSession;
    private String message;

    public boolean getIsNewSession() {
        return isNewSession;
    }
}
