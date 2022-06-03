package br.com.calculadora.interest.models.entities.enums;

import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.models.mocks.OperationEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InterestTypeTest {

    @Test
    public void ShouldTestSimpleInterestTypeGetters() {
        OperationEntity operationEntity =
                OperationEntityBuilder.builder()
                .withSimpleInterest()
                .build();

        String simpleInterestAttributes = operationEntity.getInterestType().getCode() +
                ", " + operationEntity.getInterestType().getDesc();

        Assertions.assertEquals("1, Simple Interest", simpleInterestAttributes);
    }

    @Test
    public void ShouldTestSimpleCompoundTypeGetters() {

        OperationEntity operationEntity =
                OperationEntityBuilder.builder()
                        .withCompoundInterest()
                        .build();

        String compoundInterestAttributes = operationEntity.getInterestType().getCode() +
                ", " + operationEntity.getInterestType().getDesc();

        Assertions.assertEquals("2, Compound Interest", compoundInterestAttributes);
    }

}
