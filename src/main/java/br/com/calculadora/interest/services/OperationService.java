package br.com.calculadora.interest.services;

import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.resources.exceptions.NullPointerException;
import br.com.calculadora.interest.validations.OperationAttributesValidation;
import org.modelmapper.MappingException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/** Classe de serviço para todos os processamentos e regras de negócio da entidade Operation
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 30/05/2022
 ** Fórmula para calcular montante:
 ** M = J + C (MONTANTE = JUROS + CAPITAL)
 ** Fórmula para calcular juros simples:
 ** J = C * I * T (JUROS = CAPITAL * TAXA DE JUROS * TEMPO INVESTIDO)
 ** Fórmula para calcular juros compostos:
 ** M = C * (1 + I) ^ T (MONTANTE = CAPITAL * (1 + TAXA DE JUROS ) ^ TEMPO INVESTIDO)
 **/
@Service
public class OperationService {

    /* Instanciação do DecimalFormat, que tem como objetivo formatar os números decimais para duas casas decimais*/
    DecimalFormat df = new DecimalFormat("###.##");

    /** Método responsável por realizar as validações de atributos inseridos pelo usuário com base
     *  no parâmetro da operação na qual o mesmo esteja buscando
     * @param operationRequest -> Recebe em seu parâmetro um objeto do tipo OperationRequest
     * @return -> Retorna um Boolean com true se as inserções forem validadas e false se estiverem incorretas */
    public OperationRequest operationDistributor(OperationRequest operationRequest){

        // Seta o timestamp atual no operationRequest
        operationRequest.setLocalDateTime(LocalDateTime.now());

        // Tenta realizar a distribuição para a equação correspondente
        try {

            /* DISTRIBUI PARA A OPERAÇÃO CORRESPONDENTE AO TIPO DE JUROS SOLICITADO*/
            switch (operationRequest.getInterestType()) {
                /* CASO O JUROS SEJA JUROS SIMPLES, É ENCAMINHADO PARA OPERAÇÃO DE JUROS SIMPLES*/
                case SIMPLE:
                    operationRequest = simpleInterestOperation(operationRequest);
                    break;

                /* CASO O JUROS SEJA JUROS COMPOSTOS, É ENCAMINHADO PARA OPERAÇÃO DE JUROS COMPOSTOS */
                case COMPOUND:
                    operationRequest = compoundInterestOperation(operationRequest);
                    break;
            }

        }

        // Caso haja algum atributo nulo
        catch (MappingException | java.lang.NullPointerException nullException){
            OperationAttributesValidation operationAttributesValidation = new OperationAttributesValidation();
            throw new NullPointerException("Os seguintes parâmetros são nulos: "
                    + operationAttributesValidation.nullAttributes(operationRequest) + ", favor preencher.");
        }

        /* RETORNA O OBJETO operationRequest */
        return operationRequest;
    }

    /** Método que realiza o cálculo dos juros simples, seta no objeto Request e retorna o OperationRequest com o valor calculado
     * Fórmula para juros simples -> J = C * i * t
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationRequest -> Recebe como parâmetro um objeto do tipo OperationRequest
     * @return Retorna o objeto OperationRequest com o valor dos juros atualizado */
    public OperationRequest simpleInterestOperation(OperationRequest operationRequest){

        // Fórmula para cálculo do juros simples da operação
        Double interest =
                operationRequest.getApplied() * (operationRequest.getInterestRate()/100) * operationRequest.getTime();

        // Setagem do juros simples da operação
        operationRequest.setInterest
                (Double.valueOf(df.format(interest).replace(",", ".")));

        /* FÓRMULA PARA CÁLCULO DO MONTANTE */
        operationRequest.setAmount
                (operationRequest.getApplied() + operationRequest.getInterest());

        // RETORNA O OperationRequest com o resultado da operação setado
        return operationRequest;

    }

    /** Método que realiza o cálculo dos juros compostos, seta no objeto DTO e retorna o operationRequest com o valor calculado
     * Fórmula para Juros compostos -> M = C * ((1 + i) ^ t))
     * @param operationRequest -> Recebe como parâmetro um objeto do tipo OperationRequest
     * @return Retorna o objeto operationRequest com o valor dos juros atualizado */
    public OperationRequest compoundInterestOperation(OperationRequest operationRequest) {

        // Fórmula para cálculo do montante dos juros compostos da operação
        Double amount =
                operationRequest.getApplied() * (Math.pow((1 + (operationRequest.getInterestRate()/100)),
                        (operationRequest.getTime() * operationRequest.getTimeCategory().getMonths())));

        // Setagem do montante dos juros compostos da operação
        operationRequest
                .setAmount(Double.valueOf(df.format(amount).replace(",", ".")));

        // FÓRMULA PARA CÁLCULO DOS JUROS DOS JUROS COMPOSTOS
        operationRequest.setInterest
                (operationRequest.getAmount() - operationRequest.getApplied());

        // RETORNA O operationRequest com o resultado da operação setado
        return operationRequest;

    }

}
