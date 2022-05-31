package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.models.requests.OperationRequest;

import java.util.ArrayList;
import java.util.List;

/** Método responsável por fazer as validações e distribuições com relação ao cálculo matemático dos juros
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 30/05/2022 **/
public class OperationAttributesValidation {

    /** Método responsável por retornar uma lista com o nome dos atributos nulos passados pelo usuário via POST
     * @param operationRequest -> Recebe em seu parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna uma lista com o nome dos atributos nulos passados pelo usuário via POST */
    public List<String> nullAttributes(OperationRequest operationRequest){

        // Instancia uma lista vazia para incluir uma listagem dos atributos que estão vazios
        List<String> attributes = new ArrayList<>();

        if(operationRequest.getApplied() == null){
            attributes.add("capital");
        }
        if(operationRequest.getInterestRate() == null){
            attributes.add("taxa");
        }
        if(operationRequest.getTime() == null){
            attributes.add("tempo");
        }
        if(operationRequest.getTimeCategory() == null){
            attributes.add("periodicidade");
        }
        if(operationRequest.getInterestType() == null){
            attributes.add("tipo");
        }

        // Retorna a lista preenchida com os atributos que estão vazios
        return attributes;
    }

}
