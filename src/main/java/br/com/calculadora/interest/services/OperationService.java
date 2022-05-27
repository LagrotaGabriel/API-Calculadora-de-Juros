package br.com.calculadora.interest.services;

import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.enums.InterestType;
import org.springframework.stereotype.Service;

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

    /** Método que realiza o cálculo do montante, seta no objeto DTO e retorna o OperationDTO com o valor calculado
     * Fórmula utilizada -> M = C + J (MONTANTE = CAPITAL INICIAL + JUROS TOTAIS)
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna o objeto OperationDTO com o valor do montante atualizado */
    public OperationDTO amountOperation(OperationDTO operationDTO){
        operationDTO.setAmount(operationDTO.getApplied() + operationDTO.getInterest());
        return operationDTO;
    }

    /** Método que realiza o cálculo do juros, seta no objeto DTO e retorna o OperationDTO com o valor calculado
     * Fórmula para juros simples -> J = C * i * t
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo OperationDTO
     * @return Retorna o objeto OperationDTO com o valor dos juros atualizado */
    public OperationDTO interestOperation(OperationDTO operationDTO){

        /* SE O TIPO DE JUROS BUSCADO PELO USUÁRIO FOR JUROS SIMPLES */
        if(operationDTO.getInterestType().equals(InterestType.SIMPLE)){

            /* FÓRMULA PARA CÁLCULO DE JUROS SIMPLES */
            operationDTO.setInterest
                    (operationDTO.getApplied() * (operationDTO.getInterestRate()/100) * operationDTO.getTime());

        }

        else if(operationDTO.getInterestType().equals(InterestType.COMPOUND)){

            operationDTO.setAmount
                    (operationDTO.getApplied() * (1 + (Math.pow(operationDTO.getInterestRate()/100, operationDTO.getTime()))));


        }

        return null;

    }

    public void appliedOperation(OperationDTO operationDTO){

    }

}
