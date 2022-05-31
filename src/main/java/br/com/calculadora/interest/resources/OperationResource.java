package br.com.calculadora.interest.resources;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.models.responses.OperationResponse;
import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.resources.exceptions.NullPointerException;
import br.com.calculadora.interest.resources.exceptions.ObjectNotFoundException;
import br.com.calculadora.interest.services.OperationService;
import br.com.calculadora.interest.services.dao.OperationDAOImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/operation")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "O Aplicativo tem como intuito realizar o cálculo de juros simples e compostos através das requisições" +
        "enviadas pelo usuário. Também realiza a consulta geral e/ou por id de cálculos realizados anteriormente")
public class OperationResource {

    @Autowired
    ModelMapperConfig modelMapper;

    @Autowired
    OperationDAOImpl operationDAO;

    @Autowired
    OperationService operationService;

    @ApiOperation(
            value="Busca por ID",
            notes="Busca uma operação já realizada anteriormente pelo ID",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação encontrada", response = OperationResponse.class),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 404, message = "Operação não encontrada", response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Erro de comunicação com a API"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<OperationResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(modelMapper.mapper().map(operationDAO.findById(id), OperationResponse.class));
    }

    @ApiOperation(
            value = "Buscar todas as operações",
            notes = "Busca todas as operações já realizadas na API",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operações encontradas", response = OperationResponse.class),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 404, message = "Operações não encontradas"),
            @ApiResponse(code = 500, message = "Erro de comunicação com a API"),
    })
    @GetMapping
    public ResponseEntity<List<OperationResponse>> findAll(){

        List<OperationEntity> operationEntities = operationDAO.findAll();

        List<OperationResponse> operationResponseList = operationEntities.stream()
                .map(x -> modelMapper.mapper().map(x, OperationResponse.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(operationResponseList);

    }

    @ApiOperation(
            value = "Realizar nova operação para descoberta de juros",
            notes = "Realiza uma nova operação matemática para descobrir os juros de uma equação. Fórmula para cálculo " +
                    "de juros simples: J = c X i X t || Fórmula para cálculo de juros compostos: M = c X ((1 + i) ^ t) " +
                    "|| Fórmula para cálculo de montante: M = c + j",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Operação realizada com sucesso", response = OperationResponse.class),
            @ApiResponse(code = 400, message = "Falha de violação de dados", response = NullPointerException.class),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 500, message = "Erro de comunicação com a API"),
    })
    @PostMapping
    public ResponseEntity<OperationResponse> create(@RequestBody OperationRequest operationRequest) {

            operationRequest  = operationService.operationDistributor(operationRequest);
            operationRequest.setLocalDateTime(LocalDateTime.now());

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/id").buildAndExpand(operationDAO.create(operationRequest).getId()).toUri();

            return ResponseEntity.created(uri)
                    .body(modelMapper.mapper().map(operationDAO.create(operationRequest), OperationResponse.class));

    }

}
