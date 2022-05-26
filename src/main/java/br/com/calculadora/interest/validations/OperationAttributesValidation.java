package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.exceptions.InvalidParametersException;
import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.enums.FetchedParameter;

/**
 * Fórmula para calcular montante:
 * m = j + c (MONTANTE = JUROS + CAPITAL)
 *
 * Fórmula para calcular juros simples:
 * j = c * i * t (JUROS = CAPITAL * TAXA DE JUROS * TEMPO INVESTIDO)
 *
 * Fórmula para calcular juros compostos:
 * m = c * (1 + i) ^ t (MONTANTE = CAPITAL * (1 + TAXA DE JUROS ) ^ TEMPO INVESTIDO) */
public class OperationAttributesValidation {

    public static Boolean attributesValidation(OperationDTO operationDTO) {

        /* SE O USUÁRIO QUISER CALCULAR O MONTANTE
         * Caso os JUROS ou o CAPITAL sejam nulos, necessários calculá-los
         * Caso ambos estejam preenchidos, é possível seguir direto para o cálculo do montante*/
        if(operationDTO.getFetchedParameter() == FetchedParameter.AMOUNT) {

            /* SE O MONTANTE ESTIVER PREENCHIDO COM ALGUM VALOR
             * Caso o montante esteja preenchido, não é necessário prosseguir */
            if(operationDTO.getAmount() != null){
                throw new InvalidParametersException("Valor buscado já preenchido na requisição");
            }
            /* SE O MONTANTE ESTIVER VAZIO
             * Realizar cálculo do montante */
            else{
                //TODO VERIFICAR SE JUROS OU CAPITAL SÃO NULOS, PARA REALIZAR CÁLCULOS DOS MESMOS
            }

        }

        /* SE O USUÁRIO QUISER CALCULAR O CAPITAL APLICADO */
        else if(operationDTO.getFetchedParameter() == FetchedParameter.APPLIED){

            /* SE O CAPITAL APLICADO ESTIVER PREENCHIDO COM ALGUM VALOR
             ** Caso o capital aplicado esteja preenchido, não é necessário prosseguir */
            if(operationDTO.getApplied() != null){
                throw new InvalidParametersException("Valor buscado já preenchido na requisição");
            }

            /* SE O CAPITAL APLICADO ESTIVER VAZIO
             ** Realizar cálculo do capital aplicado */
            else{
                //TODO VERIFICAR SE JUROS, TAXA DE JUROS E/OU TEMPO SÃO NULOS, PARA EM SEGUIDA PROSSEGUIR COM CÁLCULOS
            }

        }

        /* SE O USUÁRIO QUISER CALCULAR A TAXA DE JUROS **/
        else if(operationDTO.getFetchedParameter() == FetchedParameter.RATE){

            /* SE A TAXA DE JUROS ESTIVER PREENCHIDA COM ALGUM VALOR
             ** Caso a taxa de juros esteja preenchida, não é necessário prosseguir **/
            if(operationDTO.getInterestRate() != null){
                throw new InvalidParametersException("Valor buscado já preenchido na requisição");
            }

            /* SE A TAXA DE JUROS ESTIVER VAZIA
             ** Realizar cálculo da taxa de juros **/
            else{
                //TODO VERIFICAR SE JUROS, CAPITAL APLICADO E/OU TEMPO SÃO NULOS, PARA EM SEGUIDA PROSSEGUIR COM CÁLCULOS
            }

        }

        /* SE O USUÁRIO QUISER CALCULAR OS JUROS **/
        else if(operationDTO.getFetchedParameter() == FetchedParameter.INTEREST){

            /* SE O JUROS ESTIVER PREENCHIDO COM ALGUM VALOR
              Caso o juros esteja preenchido, não é necessário prosseguir **/
            if(operationDTO.getInterest() != null){
                throw new InvalidParametersException("Valor buscado já preenchida na requisição");
            }

            /* SE O JUROS ESTIVER VAZIO
             ** Realizar cálculo do juros **/
            else{
                //TODO VERIFICAR SE CAPITAL APLICADO, TAXA DE JUROS E/OU TEMPO SÃO NULOS, PARA EM SEGUIDA PROSSEGUIR COM CÁLCULOS
            }

        }

        return null; //TODO TRATAR RETORNOS DO MÉTODO
    }

}
