package br.com.calculadora.interest.resources.exceptions;

public class InvalidParametersException extends RuntimeException{
    public InvalidParametersException(String message) {
        super(message);
    }
}
