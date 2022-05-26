package br.com.calculadora.interest.services;

import br.com.calculadora.interest.models.dto.OperationDTO;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    /** Método que realiza conversão do tempo da fórmula de cálculo de juros para dias.
     * 1 MES -> 30 dias
     * 1 BIMESTRE -> 60 dias
     * 1 TRIMESTRE -> 90 dias
     * 1 SEMESTRE -> 180 dias
     * 1 ANO -> 360 dias
     * @param operationDTO -> Recebe um objeto do tipo OperationDTO
     * @return -> Retorna um valor do tipo inteiro que é a quantidade de dias na qual os juros devem trabalhar */
    public Integer convertTimeToDays(OperationDTO operationDTO){

        /* SE O TEMPO FOR DIFERENTE DE NULO:
         * Deve ser realizada a conversão do tempo.
         * SE O TEMPO FOR NULO:
         * O método irá retornar null */
        if(operationDTO.getTime() != null) return operationDTO.getTimeCategory().getDays() * operationDTO.getTime();
        return null;

    }

    /** Método que realiza o cálculo do montante, seta no objeto DTO e retorna esse total
     * Fórmula utilizada -> M = C + J (MONTANTE = CAPITAL INICIAL + JUROS TOTAIS)
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna um valor do tipo double que é o total do montante da operação */
    public Double amountOperation(OperationDTO operationDTO){
        Double sum = operationDTO.getApplied() + operationDTO.getInterest();
        operationDTO.setAmount(sum);
        return sum;
    }

    public void interestOperation(OperationDTO operationDTO){

    }

}
