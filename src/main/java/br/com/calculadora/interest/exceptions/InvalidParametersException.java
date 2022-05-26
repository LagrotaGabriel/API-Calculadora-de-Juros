package br.com.calculadora.interest.exceptions;

public class InvalidParametersException extends RuntimeException{
    public InvalidParametersException(String message) {
        super(message);
    }
}
