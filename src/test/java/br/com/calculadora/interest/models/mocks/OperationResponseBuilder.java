package br.com.calculadora.interest.models.mocks;

import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;
import br.com.calculadora.interest.models.responses.OperationResponse;

import java.time.LocalDateTime;

public class OperationResponseBuilder {

    private OperationResponse operationResponse;
    private OperationResponseBuilder(){}

    public static OperationResponseBuilder builder(){

        OperationResponseBuilder builder = new OperationResponseBuilder();
        builder.operationResponse = new OperationResponse();

        builder.operationResponse.setId(1L);
        builder.operationResponse.setLocalDateTime(LocalDateTime.now());
        builder.operationResponse.setApplied(1000.0);
        builder.operationResponse.setInterestRate(10.0);
        builder.operationResponse.setTime(1);
        builder.operationResponse.setInterest(100.0);
        builder.operationResponse.setAmount(1100.0);
        builder.operationResponse.setTimeCategory(TimeCategory.MONTH);
        builder.operationResponse.setInterestType(InterestType.SIMPLE);

        return builder;
    }

    public OperationResponseBuilder withCompoundInterest(){
        operationResponse.setInterestType(InterestType.COMPOUND);
        return this;
    }

    public OperationResponseBuilder withSimpleInterest(){
        operationResponse.setInterestType(InterestType.SIMPLE);
        return this;
    }

    public OperationResponse build(){
        return operationResponse;
    }

}
