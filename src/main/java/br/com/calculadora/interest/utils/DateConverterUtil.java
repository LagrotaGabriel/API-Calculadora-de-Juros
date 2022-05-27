package br.com.calculadora.interest.utils;

import br.com.calculadora.interest.models.dto.OperationDTO;

public class DateConverterUtil {

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

    /** Método que realiza conversão do tempo da fórmula de cálculo de juros para meses.
     * 1 DIA -> 0.33 meses
     * 1 MES -> 1 mês
     * 1 BIMESTRE -> 2 meses
     * 1 TRIMESTRE -> 3 meses
     * 1 SEMESTRE -> 6 meses
     * 1 ANO -> 12 meses
     * @param operationDTO -> Recebe um objeto do tipo OperationDTO
     * @return -> Retorna um valor do tipo Double que é a quantidade de meses na qual os juros devem trabalhar */
    public Double convertTimeToMonths(OperationDTO operationDTO){

        /* SE O TEMPO FOR DIFERENTE DE NULO:
         * Deve ser realizada a conversão do tempo.
         * SE O TEMPO FOR NULO:
         * O método irá retornar null */
        if(operationDTO.getTime() != null) return operationDTO.getTimeCategory().getMonths() * operationDTO.getTime();
        return null;

    }

}
