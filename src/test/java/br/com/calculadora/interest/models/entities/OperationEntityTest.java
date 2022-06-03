package br.com.calculadora.interest.models.entities;

import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;
import br.com.calculadora.interest.models.mocks.OperationEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class OperationEntityTest {

    @Test
    public void shouldTestOperationEntitySetters(){

        OperationEntity operationEntity = OperationEntityBuilder.builder().build();

        Assertions.assertEquals("OperationEntity(id=1, localDateTime=" +
                        operationEntity.getLocalDateTime() + ", applied=1000.0, interestRate=10.0, time=1," +
                " interest=100.0, amount=1100.0, timeCategory=MONTH, interestType=SIMPLE)",
                operationEntity.toString());

    }

    @Test
    public void shouldTestOperationEntityAllArgsConstructor(){

        OperationEntity operationEntity = new OperationEntity(
                1L,
                LocalDateTime.now(),
                1000.0,
                10.0,
                1,
                100.0,
                1100.0,
                TimeCategory.MONTH,
                InterestType.SIMPLE
                );

        Assertions.assertEquals("OperationEntity(id=1, localDateTime=" + operationEntity.getLocalDateTime() +
                        ", applied=1000.0, interestRate=10.0, time=1, interest=100.0, amount=1100.0, timeCategory=MONTH, " +
                        "interestType=SIMPLE)", operationEntity.toString());

    }

    @Test
    public void shouldTestOperationEntityBuilderAnotation(){

        OperationEntity operationEntity = OperationEntity.builder()
                .id(1L)
                .localDateTime(LocalDateTime.now())
                .applied(1000.0)
                .interestRate(10.0)
                .time(1)
                .interest(100.0)
                .amount(1100.0)
                .timeCategory(TimeCategory.MONTH)
                .interestType(InterestType.SIMPLE)
                .build();

        Assertions.assertEquals("OperationEntity(id=1, localDateTime=" + operationEntity.getLocalDateTime() +
                ", applied=1000.0, interestRate=10.0, time=1, interest=100.0, amount=1100.0, timeCategory=MONTH, " +
                "interestType=SIMPLE)", operationEntity.toString());

    }

}
