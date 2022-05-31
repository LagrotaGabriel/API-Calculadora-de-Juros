package br.com.calculadora.interest.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@Builder
public class StandartError {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String path;

}
