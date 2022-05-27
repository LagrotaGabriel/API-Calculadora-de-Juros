package br.com.calculadora.interest.resources;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.exceptions.HttpMessageNotReadableException;
import br.com.calculadora.interest.exceptions.InvalidParametersException;
import br.com.calculadora.interest.exceptions.NullPointerException;
import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.services.OperationService;
import br.com.calculadora.interest.services.dao.OperationDAOImpl;
import br.com.calculadora.interest.validations.OperationAttributesValidation;
import com.fasterxml.jackson.core.JsonParseException;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
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

    OperationAttributesValidation operationAttributesValidation = new OperationAttributesValidation();

    @GetMapping(value = "/{id}")
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

        try{
            operationDTO.setLocalDateTime(LocalDateTime.now());
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/id").buildAndExpand(operationDAO.create(operationDTO).getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        catch (MappingException mappingException){
            throw new NullPointerException("Os seguintes parâmetros são nulos: "
                    + operationAttributesValidation.nullAttributes(operationDTO) + ", favor preencher.");
        }
//        catch (Exception e){
//            System.err.println("EXCEPTION ACESSADA");
//            throw new HttpMessageNotReadableException
//                    ("Entradas incorretas. Favor preencher os campos corretamente");
//        } //TODO TRATAR INSERÇÃO DE CARACTERES ERRADOS NO POST

    }

}
