package br.com.calculadora.interest.services;

import br.com.calculadora.interest.models.mocks.OperationRequestBuilder;
import br.com.calculadora.interest.models.requests.OperationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperationServiceTest {

    @InjectMocks
    OperationService operationService;

    @Test
    @DisplayName("Should access distributor engine with simple interest type")
    public void shouldTestOperationDistributorWithSimpleInterestType(){

        OperationRequest operationRequest = OperationRequestBuilder.builder().withSimpleInterest().build();
        operationService.operationDistributor(operationRequest);

        Assertions.assertEquals("OperationRequest(localDateTime=" + operationRequest.getLocalDateTime() +
                ", applied=1000.0, interestRate=10.0, amount=1100.0, interest=100.0, time=1, timeCategory=MONTH, " +
                "interestType=SIMPLE)", operationRequest.toString());

    }

    @Test
    @DisplayName("Should access distributor engine with compound interest type")
    public void shouldTestOperationDistributorWithCompoundInterestType(){

        OperationRequest operationRequest = OperationRequestBuilder.builder().withCompoundInterest().build();
        operationService.operationDistributor(operationRequest);

        Assertions.assertEquals("OperationRequest(localDateTime=" + operationRequest.getLocalDateTime() +
                ", applied=1000.0, interestRate=10.0, amount=1100.0, interest=100.0, time=1, timeCategory=MONTH, " +
                "interestType=COMPOUND)", operationRequest.toString());

    }

    @Test
    @DisplayName("Should throw a NullPointerException in distributor engine")
    public void shouldThrowNullPointerExceptionAtOperationDistributor(){

        OperationRequest operationRequest = OperationRequestBuilder.nullOperationBuilder().build();

        try{
            operationService.operationDistributor(operationRequest);
            Assertions.fail();
        }
        catch(Exception e){
            Assertions.assertEquals("Os seguintes parâmetros são nulos: [capital, taxa, tempo, periodicidade, " +
                    "tipo], favor preencher.", e.getMessage());
        }

    }
}
