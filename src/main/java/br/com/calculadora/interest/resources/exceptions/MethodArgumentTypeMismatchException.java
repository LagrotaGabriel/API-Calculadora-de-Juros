package br.com.calculadora.interest.resources.exceptions;

public class MethodArgumentTypeMismatchException extends RuntimeException{

    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }

}
