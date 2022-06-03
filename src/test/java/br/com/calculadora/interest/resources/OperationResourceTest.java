package br.com.calculadora.interest.resources;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.models.mocks.OperationEntityBuilder;
import br.com.calculadora.interest.models.mocks.OperationRequestBuilder;
import br.com.calculadora.interest.models.responses.OperationResponse;
import br.com.calculadora.interest.services.OperationService;
import br.com.calculadora.interest.services.dao.OperationDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OperationResourceTest {

    @InjectMocks
    OperationResource operationResource;

    @Mock
    ModelMapperConfig modelMapper;

    @Mock
    OperationDAOImpl operationDAO;

    @Mock
    OperationService operationService;

    @Test
    @DisplayName("Should test find by id method with success")
    public void shouldTestResourceFindByIdMethodWithSuccess(){

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
        Mockito.when(operationDAO.findById(Mockito.any())).thenReturn(OperationEntityBuilder.builder().build());

        ResponseEntity<OperationResponse> operationResponse = operationResource.findById(1L);

        Assertions.assertEquals("<200 OK OK,OperationResponse(id=1, localDateTime=" +
                Objects.requireNonNull(operationResponse.getBody()).getLocalDateTime() + ", applied=1000.0, " +
                "interestRate=10.0, amount=1100.0, interest=100.0, time=1, timeCategory=MONTH, interestType=SIMPLE),[]>",
                operationResponse.toString());

    }

    @Test
    @DisplayName("Should test find all method with success")
    public void shouldTestResourceFindAllMethodWithSuccess(){

        Mockito.when(operationDAO.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<OperationResponse>> operationList = operationResource.findAll();

        Assertions.assertEquals("<200 OK OK,[],[]>", operationList.toString());

    }

    @Test
    @DisplayName("Should test create method with success")
    public void shouldTestCreationMethodWithSuccess(){

        Mockito.when(operationDAO.create(Mockito.any()))
                .thenReturn(OperationEntityBuilder.builder().build());

        Mockito.when(operationService.operationDistributor(Mockito.any()))
                .thenReturn(OperationRequestBuilder.builder().build());

        Mockito.when(modelMapper.mapper())
                .thenReturn(new ModelMapper());

        ResponseEntity<OperationResponse> operationResponse =
                operationResource.create(OperationRequestBuilder.builder().build());

        Assertions.assertEquals("<201 CREATED Created,OperationResponse(id=1, localDateTime=" +
                Objects.requireNonNull(operationResponse.getBody()).getLocalDateTime() +", applied=1000.0, " +
                "interestRate=10.0, amount=1100.0, interest=100.0, time=1, timeCategory=MONTH, interestType=SIMPLE)," +
                "[Location:\"http://localhost/id\"]>", operationResponse.toString());

    }

}
