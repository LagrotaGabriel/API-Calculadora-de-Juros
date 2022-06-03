package br.com.calculadora.interest.resources.exceptions;

import lombok.*;

import java.time.LocalDateTime;

/** Classe utilizada para padronizar o ResponseBody das Exceptions personalizadas do projeto de acordo com seus atributos
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StandartError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String path;

}
