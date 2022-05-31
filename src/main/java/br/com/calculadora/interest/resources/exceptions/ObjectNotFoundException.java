package br.com.calculadora.interest.resources.exceptions;

/** Classe de exceção personalizada para falhas de ObjectNotFound
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 */
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message) {
        super(message);
    }

}
