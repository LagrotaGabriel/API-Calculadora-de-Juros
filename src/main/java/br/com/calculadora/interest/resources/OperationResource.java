package br.com.calculadora.interest.resources;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.entities.OperationEntity;
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

    public static final String ID = "/{id}";

    @GetMapping(value = ID)
    public ResponseEntity<OperationDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(modelMapper.mapper().map(operationDAO.findById(id), OperationDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<OperationDTO>> findAll(){

        List<OperationEntity> operationEntities = operationDAO.findAll();

        List<OperationDTO> operationDTOS = operationEntities.stream()
                .map(x -> modelMapper.mapper().map(x, OperationDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(operationDTOS);

    }

    @PostMapping
    public ResponseEntity<OperationDTO> create(@RequestBody OperationDTO operationDTO){

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(operationDAO.create(operationDTO).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
