package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Fórmula para calcular montante:
 * m = j + c (MONTANTE = JUROS + CAPITAL)
 *
 * Fórmula para calcular juros simples:
 * j = c * i * t (JUROS = CAPITAL * TAXA DE JUROS * TEMPO INVESTIDO)
 *
 * Fórmula para calcular juros compostos:
 * m = c * (1 + i) ^ t (MONTANTE = CAPITAL * (1 + TAXA DE JUROS ) ^ TEMPO INVESTIDO)
 **/
public class OperationAttributesValidation {

    /* Instanciação da classe de processamento da entidade OperationServiceEntity,
     * que tem como objetivo realizar os cálculos e processamentos do sistema */
    @Autowired OperationService operationService;

    /** Método responsável por retornar uma lista com o nome dos atributos nulos passados pelo usuário via POST
     * @param operationDTO -> Recebe em seu parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna uma lista com o nome dos atributos nulos passados pelo usuário via POST */
    public List<String> nullAttributes(OperationDTO operationDTO){

        List<String> attributes = new ArrayList<>();

        if(operationDTO.getApplied() == null){
            attributes.add("applied");
        }
        if(operationDTO.getInterestRate() == null){
            attributes.add("interestRate");
        }
        if(operationDTO.getTime() == null){
            attributes.add("time");
        }
        if(operationDTO.getTimeCategory() == null){
            attributes.add("timeCategory");
        }
        if(operationDTO.getInterestType() == null){
            attributes.add("interestType");
        }

        return attributes;
    }

    /** Método responsável por realizar as validações de atributos inseridos pelo usuário com base
     *  no parâmetro da operação na qual o mesmo esteja buscando
     * @param operationDTO -> Recebe em seu parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna um Boolean com true se as inserções forem validadas e false se estiverem incorretas */
    public OperationDTO attributesValidation(OperationDTO operationDTO) {


        return operationDTO;
    }
}
