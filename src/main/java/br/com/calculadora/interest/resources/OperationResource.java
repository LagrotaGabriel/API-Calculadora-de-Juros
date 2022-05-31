package br.com.calculadora.interest.resources;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.models.responses.OperationResponse;
import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.services.OperationService;
import br.com.calculadora.interest.services.dao.OperationDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/operation")
public class OperationResource {

    @Autowired
    ModelMapperConfig modelMapper;

    @Autowired
    OperationDAOImpl operationDAO;

    @Autowired
    OperationService operationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OperationResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(modelMapper.mapper().map(operationDAO.findById(id), OperationResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<OperationResponse>> findAll(){

        List<OperationEntity> operationEntities = operationDAO.findAll();

        List<OperationResponse> operationResponseList = operationEntities.stream()
                .map(x -> modelMapper.mapper().map(x, OperationResponse.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(operationResponseList);

    }

    @PostMapping
    public ResponseEntity<OperationResponse> create(@RequestBody OperationRequest operationRequest) {

            OperationResponse operationResponse = operationService.operationDistributor(operationRequest);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/id").buildAndExpand(operationDAO.create(operationRequest).getId()).toUri();

            return ResponseEntity.created(uri).body(operationResponse);

    }

}
