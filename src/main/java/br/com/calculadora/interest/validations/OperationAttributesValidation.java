package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.exceptions.InvalidParametersException;
import br.com.calculadora.interest.exceptions.NullPointerException;
import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.enums.FetchedParameter;
import br.com.calculadora.interest.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class OperationAttributesValidation {

    /* Instanciação da classe de processamento da entidade OperationServiceEntity,
     * que tem como objetivo realizar os cálculos e processamentos do sistema */
    @Autowired OperationService operationService;

    /** Método que tem como objetivo validar a quantidade de itens da operação que são nulos
     * Caso mais de um item seja nulo, a operação não pode ser realizada
     * @param operationDTO -> Recebe como parâmetro um objeto do tipo operationDTO
     * @return -> Retorna um Boolean com false caso mais de um item seja null e com true caso menos de um item seja null */
    public Boolean nullAttributesValidation(OperationDTO operationDTO){

        int cont = 0;

        if(operationDTO.getInterest() == null){
            cont += 1;
        }
        else if(operationDTO.getApplied() == null){
            cont += 1;
        }
        else if(operationDTO.getInterestRate() == null){
            cont += 1;
        }
        else if(operationDTO.getTime() == null){
            cont += 1;
        }

        if(cont > 1) return false;
        return true;

    }

    /** Método responsável por realizar as validações de atributos inseridos pelo usuário com base
     *  no parâmetro da operação na qual o mesmo esteja buscando
     * @param operationDTO -> Recebe em seu parâmetro um objeto do tipo OperationDTO
     * @return -> Retorna um Boolean com true se as inserções forem validadas e false se estiverem incorretas */
    public Boolean attributesValidation(OperationDTO operationDTO) {

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

                /* SE O JUROS E O CAPITAL APLICADO ESTIVEREM PREENCHIDOS
                 * O cálculo do montante é realizado direto, sem necessidade de cálculos adicionais para chegar ao
                 * seu resultado*/
                if(operationDTO.getInterest() != null && operationDTO.getApplied() != null){
                    operationService.amountOperation(operationDTO);
                }

                /* SE O JUROS OU O CAPITAL APLICADO NÃO ESTIVEREM PREENCHIDOS
                 * Será necessário validar quantos campos não estão preenchidos
                 * Se existir mais de um campo sem preenchimento, não e possível realizar a operação */
                else{

                    /* SE MAIS DE UM ATRIBUTO DA OPERAÇÃO MATEMÁTICA ESTIVER NULO
                     * Não é possível prosseguir com a operação, lançando uma operação do tipo NullPointerException */
                    if(!nullAttributesValidation(operationDTO)){
                        throw new NullPointerException("Não é possível realizar a operação matemática com mais de um" +
                                "atributo não definido");
                    }

                    /* SE APENAS UM ATRIBUTO DA OPERAÇÃO MATEMÁTICA ESTIVER NULO
                     * Descobrir qual é o valor nulo da operação, e com base nisso descobrir a
                     * incognita da operação */
                    else{

                        if(operationDTO.getInterest() == null){
                            // TODO OPERAÇÃO DE CÁLCULO DE JUROS
                        }
                        if(operationDTO.getApplied() == null){
                            // TODO OPERAÇÃO DE CÁLCULO DE CAPITAL
                        }

                    }

                }

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
