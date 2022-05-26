package br.com.calculadora.interest.services;

import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.enums.FetchedParameter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationService {

    public Integer convertTimeToDays(OperationDTO operationDTO){
        if(operationDTO.getTime() != null) return operationDTO.getTimeCategory().getDays() * operationDTO.getTime();
        return null;
    }

    public void interestOperation(OperationDTO operationDTO){

    }

}
