package br.com.verex.avaliacao.error;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException {

    private String message;

    public BadRequestException(final String message) {
        this(message, null);
    }

    public BadRequestException(final String message, final List<String> errors) {
        this.errors = errors;
        this.message = message;
    }

    private final List<String> errors;

}