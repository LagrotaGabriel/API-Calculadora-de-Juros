package br.com.calculadora.interest.validations;

import br.com.calculadora.interest.models.mocks.OperationRequestBuilder;
import br.com.calculadora.interest.models.requests.OperationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OperationAttributesValidationTest {

    OperationAttributesValidation operationAttributesValidation = new OperationAttributesValidation();

    @Test
    public void shouldTestNullAttributesValidationWithNotNullAttributes(){

        OperationRequest operationRequest = OperationRequestBuilder.builder().build();

        List<String> nullItemsList = operationAttributesValidation.nullAttributes(operationRequest);

        Assertions.assertEquals(new ArrayList<>(), nullItemsList);

    }

    @Test
    public void shouldTestNulLAttributesValidationWithNullAttributes(){

        OperationRequest operationRequest = OperationRequestBuilder.nullOperationBuilder().build();

        List<String> nullItemsList = operationAttributesValidation.nullAttributes(operationRequest);
        List<String> fixedList = new ArrayList<>(Arrays.asList("capital", "taxa", "tempo", "periodicidade", "tipo"));

        Assertions.assertEquals(fixedList, nullItemsList);
    }
}
