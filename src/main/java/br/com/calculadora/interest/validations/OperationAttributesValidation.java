package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.services.OperationService;

import java.util.ArrayList;
import java.util.List;

/** Método responsável por fazer as validações e distribuições com relação ao cálculo matemático dos juros
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 30/05/2022
 **/
public class OperationAttributesValidation {

    /* Instanciação da classe de processamento da entidade OperationServiceEntity,
     * que tem como objetivo realizar os cálculos e processamentos do sistema */
    OperationService operationService = new OperationService();

    /** Método responsável por retornar uma lista com o nome dos atributos nulos passados pelo usuário via POST
     * @param operationDTO -> Recebe em seu parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna uma lista com o nome dos atributos nulos passados pelo usuário via POST */
    public List<String> nullAttributes(OperationDTO operationDTO){

        // Instancia uma lista vazia para incluir uma listagem dos atributos que estão vazios
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

        // Retorna a lista preenchida com os atributos que estão vazios
        return attributes;
    }

    /** Método responsável por realizar as validações de atributos inseridos pelo usuário com base
     *  no parâmetro da operação na qual o mesmo esteja buscando
     * @param operationDTO -> Recebe em seu parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna um Boolean com true se as inserções forem validadas e false se estiverem incorretas */
    public OperationDTO operationValidation(OperationDTO operationDTO) {
        
        /* DISTRIBUI PARA A OPERAÇÃO CORRESPONDENTE AO TIPO DE JUROS SOLICITADO*/
        switch(operationDTO.getInterestType()){
            
            /* CASO O JUROS SEJA JUROS SIMPLES, É ENCAMINHADO PARA OPERAÇÃO DE JUROS SIMPLES*/
            case SIMPLE:
                operationDTO = operationService.simpleInterestOperation(operationDTO);
                break;

            /* CASO O JUROS SEJA JUROS COMPOSTOS, É ENCAMINHADO PARA OPERAÇÃO DE JUROS COMPOSTOS */
            case COMPOUND:
                operationDTO = operationService.compoundInterestOperation(operationDTO);
                break;

        }

        /* RETORNA O OBJETO operationDTO*/
        return operationDTO;
    }
}
