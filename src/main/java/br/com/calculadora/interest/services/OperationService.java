package br.com.calculadora.interest.services;

import br.com.calculadora.interest.exceptions.InvalidParametersException;
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

    /** Método que realiza o cálculo do montante, seta no objeto DTO e retorna o OperationDTO com o valor calculado
     * Fórmula utilizada -> M = C + J (MONTANTE = CAPITAL INICIAL + JUROS TOTAIS)
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna o objeto OperationDTO com o valor do montante atualizado */
    public OperationDTO amountOperation(OperationDTO operationDTO){
        operationDTO.setAmount(operationDTO.getApplied() + operationDTO.getInterest());
        return operationDTO;
    }

    /** Método que realiza o cálculo dos juros simples, seta no objeto DTO e retorna o OperationDTO com o valor calculado
     * Fórmula para juros simples -> J = C * i * t
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo OperationDTO
     * @return Retorna o objeto OperationDTO com o valor dos juros atualizado */
    public OperationDTO simpleInterestOperation(OperationDTO operationDTO) throws InvalidParametersException {

        /* SE O TIPO DE JUROS BUSCADO PELO USUÁRIO FOR JUROS SIMPLES */
        if(operationDTO.getInterestType().equals(InterestType.SIMPLE)){
            /* FÓRMULA PARA CÁLCULO DE JUROS SIMPLES */
            operationDTO.setInterest
                    (operationDTO.getApplied() * (operationDTO.getInterestRate()/100) * operationDTO.getTime());
        }

        /* SE O TIPO DE JUROS BUSCADO PELO USUÁRIO FOR JUROS COMPOSTOS */
        else if(operationDTO.getInterestType().equals(InterestType.COMPOUND)){

            // FÓRMULA PARA CÁLCULO DE MONTANTE DOS JUROS COMPOSTOS
            operationDTO.setAmount
                    (operationDTO.getApplied() * (1 + (Math.pow(operationDTO.getInterestRate()/100, operationDTO.getTime()))));

            // FÓRMULA PARA CÁLCULO DOS JUROS DOS JUROS COMPOSTOS
            operationDTO.setInterest
                    (operationDTO.getAmount() - operationDTO.getApplied());

        }

        // RETORNA O operationDTO com o resultado da operação setado
        return operationDTO;

    }

    /** Método que realiza o cálculo dos juros compostos, seta no objeto DTO e retorna o OperationDTO com o valor calculado
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo OperationDTO
     * @return Retorna o objeto OperationDTO com o valor dos juros atualizado */
    public OperationDTO compoundInterestOperation(OperationDTO operationDTO) throws InvalidParametersException {

        // FÓRMULA PARA CÁLCULO DE MONTANTE DOS JUROS COMPOSTOS
        operationDTO.setAmount
                (operationDTO.getApplied() * (1 + (Math.pow(operationDTO.getInterestRate()/100, operationDTO.getTime()))));

        // FÓRMULA PARA CÁLCULO DOS JUROS DOS JUROS COMPOSTOS
        operationDTO.setInterest
                (operationDTO.getAmount() - operationDTO.getApplied());

        // RETORNA O operationDTO com o resultado da operação setado
        return operationDTO;

    }

}
