package br.com.calculadora.interest.resources.exceptions;

public class HttpMessageNotReadableException extends RuntimeException{

    public HttpMessageNotReadableException(String message) {
        super(message);
    }

}
