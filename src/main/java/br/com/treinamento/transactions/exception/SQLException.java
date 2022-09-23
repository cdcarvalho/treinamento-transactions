package br.com.treinamento.transactions.exception;

import lombok.Getter;

@Getter
public class SQLException extends RuntimeException {

    private String message;

    public SQLException(final String message) {
        this.message = message;
    }
}
