package br.com.calculadora.interest.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/** Classe que tem como objetivo ser uma espécie de motor de redirecionamento e tratamento de exceções
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 **/
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> ObjectNotFound(ObjectNotFoundException objectNotFoundException,
                                                        HttpServletRequest httpServletRequest){

        StandartError error = new StandartError(LocalDateTime.now(),
                        404, objectNotFoundException.getMessage(), httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandartError> nullPointerException(NullPointerException nullPointerException,
                                                              HttpServletRequest httpServletRequest){

        StandartError error = new StandartError(LocalDateTime.now(),
                400, nullPointerException.getMessage(), httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandartError> NumberFormatException(HttpServletRequest httpServletRequest){

        StandartError error = new StandartError
                (LocalDateTime.now(), 400, "Falha de violação de dados. Favor inserir o tipo correto de entrada",
                        httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
