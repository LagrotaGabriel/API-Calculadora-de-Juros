package br.com.calculadora.interest.models.mocks;

import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;

import java.time.LocalDateTime;

public class OperationEntityBuilder {

    private OperationEntity operationEntity;
    private OperationEntityBuilder(){}

    public static OperationEntityBuilder builder() {

        OperationEntityBuilder builder = new OperationEntityBuilder();
        builder.operationEntity = new OperationEntity();

        builder.operationEntity.setId(1L);
        builder.operationEntity.setLocalDateTime(LocalDateTime.now());
        builder.operationEntity.setApplied(1000.0);
        builder.operationEntity.setInterestRate(10.0);
        builder.operationEntity.setTime(1);
        builder.operationEntity.setInterest(100.0);
        builder.operationEntity.setAmount(1100.0);
        builder.operationEntity.setTimeCategory(TimeCategory.MONTH);
        builder.operationEntity.setInterestType(InterestType.SIMPLE);

        return builder;

    }

    public OperationEntityBuilder withCompoundInterest(){
        operationEntity.setInterestType(InterestType.COMPOUND);
        return this;
    }

    public OperationEntityBuilder withSimpleInterest(){
        operationEntity.setInterestType(InterestType.SIMPLE);
        return this;
    }

    public OperationEntityBuilder withDayTimeCategory(){
        operationEntity.setTimeCategory(TimeCategory.DAY);
        return this;
    }

    public OperationEntityBuilder withMonthTimeCategory(){
        operationEntity.setTimeCategory(TimeCategory.MONTH);
        return this;
    }

    public OperationEntityBuilder withBimesterTimeCategory(){
        operationEntity.setTimeCategory(TimeCategory.BIMESTER);
        return this;
    }

    public OperationEntityBuilder withTrimesterTimeCategory(){
        operationEntity.setTimeCategory(TimeCategory.TRIMESTER);
        return this;
    }

    public OperationEntityBuilder withSemesterTimeCategory(){
        operationEntity.setTimeCategory(TimeCategory.SEMESTER);
        return this;
    }

    public OperationEntityBuilder withYearTimeCategory(){
        operationEntity.setTimeCategory(TimeCategory.YEAR);
        return this;
    }

    public OperationEntityBuilder withStaticLocalDateTime(){
        operationEntity.setLocalDateTime(LocalDateTime.parse("2022-06-02T17:15:46.518800100"));
        return this;
    }

    public OperationEntity build(){
        return operationEntity;
    }

}
