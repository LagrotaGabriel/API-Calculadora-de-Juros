package br.com.calculadora.interest.resources.exceptions;

/** Classe de exceção personalizada para falhas de NullPointer
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 */
public class NullPointerException extends RuntimeException{

    public NullPointerException(String message) {
        super(message);
    }

}
