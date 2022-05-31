package br.com.calculadora.interest.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Classe utilizada para padronizar o ResponseBody das Exceptions personalizadas do projeto de acordo com seus atributos
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class StandartError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String path;

}
