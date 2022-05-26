package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.enums.FetchedParameter;

public class OperationAttributesValidation {

    public void attributesValidation (OperationDTO operationDTO){

        if(operationDTO.getFetchedParameter() == FetchedParameter.AMOUNT){

        }

    }

}
