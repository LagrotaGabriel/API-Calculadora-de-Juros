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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OperationDAOImplTest {

    @InjectMocks
    private OperationDAOImpl operationDAO;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private ModelMapperConfig modelMapper;

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

        Mockito.when(operationRepository.findAll()).thenReturn(operationEntityList);

        operationDAO.findAll();

        Assertions.assertEquals("[OperationEntity(id=1, localDateTime=2022-06-02T17:15:46.518800100, " +
                "applied=1000.0, interestRate=10.0, time=1, interest=100.0, amount=1100.0, timeCategory=MONTH, " +
                "interestType=SIMPLE), OperationEntity(id=1, localDateTime=2022-06-02T17:15:46.518800100, applied=1000.0, " +
                "interestRate=10.0, time=1, interest=100.0, amount=1100.0, timeCategory=BIMESTER, interestType=COMPOUND)]",
                operationEntityList.toString());

    }

    @Test
    @DisplayName("Should test find by id method in implementation with ObjectNotFoundException")
    public void shouldTestOperationDAOImplFindByIdMethodWithException(){

        try {
            Mockito.when(operationRepository.findById(Mockito.any())).thenReturn(Optional.empty());
            operationDAO.findById(1L);
            Assertions.fail();
        }
        catch(ObjectNotFoundException e){
            Assertions.assertEquals("Objeto não encontrado", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test find by id method in implementation with success")
    public void shouldTestOperationDAOImplFindByIdMethodWithSuccess() throws ObjectNotFoundException{

        Mockito.when(operationRepository.findById(Mockito.any())).thenReturn(Optional.of(OperationEntityBuilder.builder().build()));
        operationDAO.findById(1L);

    }

    @Test
    @DisplayName("Should test creation method in implementation with success")
    public void shouldTestOperationDAOImplCreationMethodWithSuccess() {

        Mockito.when(operationRepository.save(Mockito.any())).thenReturn(OperationEntityBuilder.builder().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());

        OperationEntity operationEntity = operationDAO.create(OperationRequestBuilder.builder().build());

    }

}
