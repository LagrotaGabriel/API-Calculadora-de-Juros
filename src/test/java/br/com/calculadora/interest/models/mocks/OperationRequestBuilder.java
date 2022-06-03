package br.com.calculadora.interest.models.mocks;

import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;
import br.com.calculadora.interest.models.requests.OperationRequest;

import java.time.LocalDateTime;

public class OperationRequestBuilder {

    private OperationRequest operationRequest;
    private OperationRequestBuilder(){}

    public static OperationRequestBuilder builder()         {

        OperationRequestBuilder builder = new OperationRequestBuilder();
        builder.operationRequest = new OperationRequest();

        builder.operationRequest.setLocalDateTime(LocalDateTime.now());
        builder.operationRequest.setApplied(1000.0);
        builder.operationRequest.setInterestRate(10.0);
        builder.operationRequest.setTime(1);
        builder.operationRequest.setInterest(100.0);
        builder.operationRequest.setAmount(1100.0);
        builder.operationRequest.setTimeCategory(TimeCategory.MONTH);
        builder.operationRequest.setInterestType(InterestType.SIMPLE);

        return builder;
    }

    public static OperationRequestBuilder nullOperationBuilder(){

        OperationRequestBuilder builder = new OperationRequestBuilder();
        builder.operationRequest = new OperationRequest();

        builder.operationRequest.setLocalDateTime(LocalDateTime.now());
        builder.operationRequest.setApplied(null);
        builder.operationRequest.setInterestRate(null);
        builder.operationRequest.setTime(null);
        builder.operationRequest.setInterest(null);
        builder.operationRequest.setAmount(null);
        builder.operationRequest.setTimeCategory(null);
        builder.operationRequest.setInterestType(null);

        return builder;
    }

    public OperationRequestBuilder withCompoundInterest(){
        operationRequest.setInterestType(InterestType.COMPOUND);
        return this;
    }

    public OperationRequestBuilder withSimpleInterest(){
        operationRequest.setInterestType(InterestType.SIMPLE);
        return this;
    }

    public OperationRequestBuilder withStaticLocalDateTime(){
        operationRequest.setLocalDateTime(LocalDateTime.parse("2022-06-02T17:15:46.518800100"));
        return this;
    }

    public OperationRequest build(){
        return operationRequest;
    }

}
