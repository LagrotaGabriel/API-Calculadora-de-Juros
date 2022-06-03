package br.com.calculadora.interest.models.responses;

import br.com.calculadora.interest.models.mocks.OperationResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperationResponseTest {

    @Test
    public void shouldTestOperationResponseSetters(){

        OperationResponse operationResponse = OperationResponseBuilder.builder().build();

        Assertions.assertEquals("OperationResponse(id=1, localDateTime=" + operationResponse.getLocalDateTime()
                + ", applied=1000.0, interestRate=10.0, amount=1100.0, interest=100.0, " +
                "time=1, timeCategory=MONTH, interestType=SIMPLE)", operationResponse.toString());

    }
}
