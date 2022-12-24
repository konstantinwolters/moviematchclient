package com.example.moviematchweb.exception;

import org.springframework.stereotype.Component;

import com.example.moviematchweb.exception.customExceptions.NoSuchRessourceException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 404: {
                return new NoSuchRessourceException("Requested ressource does not exist.");
            }
            default: {
                return defaultDecoder.decode(methodKey, response);
            }
        }
    }
}
