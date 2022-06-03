package br.com.calculadora.interest.models.requests;

import br.com.calculadora.interest.models.mocks.OperationRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperationRequestTest {

    @Test
    public void shouldTestOperationRequestSetters(){

        OperationRequest operationRequest = OperationRequestBuilder.builder().build();

        Assertions.assertEquals("OperationRequest(localDateTime=" + operationRequest.getLocalDateTime() + ", " +
                "applied=1000.0, interestRate=10.0, amount=1100.0, interest=100.0, time=1, " +
                "timeCategory=MONTH, interestType=SIMPLE)", operationRequest.toString());


    }

}
