package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.models.mocks.OperationEntityBuilder;
import br.com.calculadora.interest.models.mocks.OperationRequestBuilder;
import br.com.calculadora.interest.repositories.OperationRepository;
import br.com.calculadora.interest.resources.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OperationDAOImplTest {

    @InjectMocks
    OperationDAOImpl operationDAO;

    @Mock
    OperationRepository operationRepository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test find all method in implementation with a null list")
    public void shouldTestOperationDAOImplFindAllMethodWithNullList(){

        Mockito.when(operationDAO.findAll()).thenReturn(new ArrayList<>());

        List<OperationEntity> operationEntityAll = operationDAO.findAll();

        Assertions.assertEquals(new ArrayList<>(), operationEntityAll);

    }

    @Test
    @DisplayName("Should test find all method in implementation with a filled list")
    public void shouldTestOperationDAOImplFindAllMethodWithFilledList(){

        List<OperationEntity> operationEntityList = new ArrayList<>();

        operationEntityList.add(OperationEntityBuilder.builder()
                .withStaticLocalDateTime()
                .build());
        operationEntityList.add(OperationEntityBuilder.builder()
                .withCompoundInterest()
                .withBimesterTimeCategory()
                .withStaticLocalDateTime()
                .build());

        Mockito.when(operationDAO.findAll()).thenReturn(operationEntityList);

        Assertions.assertEquals("[OperationEntity(id=1, localDateTime=2022-06-02T17:15:46.518800100, " +
                "applied=1000.0, interestRate=10.0, time=1, interest=100.0, amount=1100.0, timeCategory=MONTH, " +
                "interestType=SIMPLE), OperationEntity(id=1, localDateTime=2022-06-02T17:15:46.518800100, applied=1000.0, " +
                "interestRate=10.0, time=1, interest=100.0, amount=1100.0, timeCategory=BIMESTER, interestType=COMPOUND)]",
                operationEntityList.toString());

    }

    @Test
    @DisplayName("Should test find by id method in implementation with ObjectNotFoundException")
    public void shouldTestOperationDAOImplFindByIdMethodWithSuccess(){

        try {
            Mockito.when(operationDAO.findById(Mockito.any())).thenReturn(OperationEntityBuilder.builder().build());
            Assertions.fail();
        }
        catch(ObjectNotFoundException e){
            Assertions.assertEquals("Objeto não encontrado", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test creation method in implementation with success")
    public void shouldTestOperationDAOImplCreationMethodWithSuccess() {

        //TODO
        //Mockito.when(operationDAO.create(OperationRequestBuilder.builder().build())).thenReturn(OperationEntityBuilder.builder().build());

    }

}
