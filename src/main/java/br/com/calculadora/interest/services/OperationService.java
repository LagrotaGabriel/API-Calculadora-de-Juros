package br.com.calculadora.interest.services;

import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.models.responses.OperationResponse;
import br.com.calculadora.interest.resources.exceptions.NullPointerException;
import br.com.calculadora.interest.validations.OperationAttributesValidation;
import org.modelmapper.MappingException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

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

    /* Instanciação do DecimalFormat, que tem como objetivo formatar os números decimais para duas casas decimais*/
    DecimalFormat df = new DecimalFormat("###.##");

    OperationResponse operationResponse = new OperationResponse();

    OperationAttributesValidation operationAttributesValidation = new OperationAttributesValidation();

    /** Método responsável por realizar as validações de atributos inseridos pelo usuário com base
     *  no parâmetro da operação na qual o mesmo esteja buscando
     * @param operationRequest -> Recebe em seu parâmetro um objeto do tipo OperationRequest
     * @return -> Retorna um Boolean com true se as inserções forem validadas e false se estiverem incorretas */
    public OperationResponse operationDistributor(OperationRequest operationRequest){

        try {

            /* DISTRIBUI PARA A OPERAÇÃO CORRESPONDENTE AO TIPO DE JUROS SOLICITADO*/
            switch (operationRequest.getInterestType()) {
                /* CASO O JUROS SEJA JUROS SIMPLES, É ENCAMINHADO PARA OPERAÇÃO DE JUROS SIMPLES*/
                case SIMPLE:
                    operationResponse = simpleInterestOperation(operationRequest);
                    break;

                /* CASO O JUROS SEJA JUROS COMPOSTOS, É ENCAMINHADO PARA OPERAÇÃO DE JUROS COMPOSTOS */
                case COMPOUND:
                    operationResponse = compoundInterestOperation(operationRequest);
                    break;
            }

        }

        catch (MappingException | java.lang.NullPointerException nullException){
            throw new NullPointerException("Os seguintes parâmetros são nulos: "
                    + operationAttributesValidation.nullAttributes(operationRequest) + ", favor preencher.");
        }

        /* RETORNA O OBJETO OperationResponse */
        return operationResponse;
    }

    /** Método que realiza o cálculo dos juros simples, seta no objeto Response e retorna o Response com o valor calculado
     * Fórmula para juros simples -> J = C * i * t
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationRequest -> Recebe como parâmetro um objeto do tipo OperationRequest
     * @return Retorna o objeto OperationResponse com o valor dos juros atualizado */
    public OperationResponse simpleInterestOperation(OperationRequest operationRequest){

        operationResponse.setLocalDateTime(LocalDateTime.now());

        Double interest = operationRequest.getApplied() * (operationRequest.getInterestRate()/100) * operationRequest.getTime();
        /* FÓRMULA PARA CÁLCULO DE JUROS SIMPLES */
        operationResponse.setInterest
                (Double.valueOf(df.format(interest).replace(",", ".")));

        /* FÓRMULA PARA CÁLCULO DO MONTANTE */
        operationResponse.setAmount
                (operationRequest.getApplied() + operationResponse.getInterest());

        // RETORNA O OperationResponse com o resultado da operação setado
        return operationResponse;

    }

    /** Método que realiza o cálculo dos juros compostos, seta no objeto DTO e retorna o OperationResponse com o valor calculado
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationRequest -> Recebe como parâmetro um objeto do tipo OperationRequest
     * @return Retorna o objeto OperationResponse com o valor dos juros atualizado */
    public OperationResponse compoundInterestOperation(OperationRequest operationRequest) {

        operationResponse.setLocalDateTime(LocalDateTime.now());

        Double amount =
                operationRequest.getApplied() * (Math.pow((1 + (operationRequest.getInterestRate()/100)),
                        (operationRequest.getTime() * operationRequest.getTimeCategory().getMonths())));

        // FÓRMULA PARA CÁLCULO DE MONTANTE DOS JUROS COMPOSTOS
        operationResponse
                .setAmount(Double.valueOf(df.format(amount).replace(",", ".")));

        // FÓRMULA PARA CÁLCULO DOS JUROS DOS JUROS COMPOSTOS
        operationResponse.setInterest
                (operationResponse.getAmount() - operationRequest.getApplied());

        // RETORNA O OperationResponse com o resultado da operação setado
        return operationResponse;

    }

}
