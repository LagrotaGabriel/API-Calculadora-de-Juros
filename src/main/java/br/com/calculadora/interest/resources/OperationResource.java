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
import java.util.List;
import java.util.stream.Collectors;

/** RestController do projeto, que tem como objetivo realizar operações de Get e Post
 ** @see [GET] api/operation/{id} -> Realiza a busca de uma operação por ID
 ** @see [GET] api/operation -> Realiza a busca de todas as operações já realizadas anteriormente
 ** @see [POST] api/operation -> Realiza o cadastro de uma nova operação no banco de dados
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 **/
@RestController
@RequestMapping(value = "api/operation")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "O Aplicativo tem como intuito realizar o cálculo de juros simples e compostos através das requisições" +
        "enviadas pelo usuário. Também realiza a consulta geral e/ou por id de cálculos realizados anteriormente")
public class OperationResource {

    // Injeção de dependência da classe de configuração ModelMapperConfig, que permite realizar a conversão de objetos
    @Autowired
    ModelMapperConfig modelMapper;

    // Injeção de dependência da classe de implementação de persistência de dados OperationDAOImpl
    @Autowired
    OperationDAOImpl operationDAO;

    // Injeção de dependência da classe de serviços OperationService
    @Autowired
    OperationService operationService;

    /** Método que tem como objetivo realizar a busca de uma operação por ID no banco de dados
     * @param id Long - Recebe como parâmetro o ID para realizar a busca no banco de dados
     * @return Retorna um objeto do tipo OperationResponse, com os atributos tratados para resposta */
    @ApiOperation(
            value="Busca por ID",
            notes="Busca uma operação já realizada anteriormente pelo ID",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação encontrada", response = OperationResponse.class),
            @ApiResponse(code = 400, message = "Violação de dados"),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 404, message = "Operação não encontrada", response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Erro de comunicação com a API"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<OperationResponse> findById(@PathVariable Long id){
        /* Retorno para o usuário de um objeto do tipo OperationResponse, contendo o objeto buscado pelo usuário */
        return ResponseEntity.ok().body(modelMapper.mapper().map(operationDAO.findById(id), OperationResponse.class));
    }

    /** Método que tem como objetivo retornar uma lista com todas as operações já realizadas anteriormente salvas no
     ** banco de dados do projeto
     ** @return retorna uma lista com Objetos do tipo OperationResponse */
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

        /* Instanciação de lista de entidades com base no método de implementação de persistência de dados
        ** operationDao.findAll() */
        List<OperationEntity> operationEntities = operationDAO.findAll();

        /* Conversão da lista de entidades operationEntities do tipo OperationEntity para uma lista do tipo
        ** OperationResponse, transformando-a assim em uma lista ideal para visualização do usuário, proporcionando
        ** encapsulamento aprimorado dos atributos da classe OperationEntity */
        List<OperationResponse> operationResponseList = operationEntities.stream()
                .map(x -> modelMapper.mapper().map(x, OperationResponse.class)).collect(Collectors.toList());

        /* Retorno da lista de operações para o usuário utilizando um oobjeto do tipo OperationResponse */
        return ResponseEntity.ok().body(operationResponseList);

    }

    /** Método que tem como objetivo cadastrar e receber uma resposta de uma nova operação de cálculo de juros no
     ** banco de dados
     ** @param operationRequest  Recebe  como parâmetro um objeto do tipo operationRequest através de um RequestBody,
     *                          com todos os atributos necessários para realizar a operação matemática para cálculo dos
     *                          juros
     ** @return  Retorna um objeto do tipo OperationResponse, com os atributos processados e persistidos no banco de dados*/
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

            /* Encaminha o objeto operationRequest recebido como parâmetro para o motor de distribuição da classe de
            ** serviços, que irá distribuí-lo para a equação matemática ideal com base no tipo de juros que o usuário
            ** deseja calcular. O operationRequest recebe os set dos resultados das equações e é reatribuído e atualizado
            ** com estes valores que foram recebidos */
            operationRequest  = operationService.operationDistributor(operationRequest);

            /* Instanciação da URI contendo a criação e persistência da operação matemática no banco de dados */
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/id").buildAndExpand(operationDAO.create(operationRequest).getId()).toUri();

            /* Retorno do ResponseBody em padrão JSON, contendo o objeto operationRequest com todos os seus atributos,
            ** porém convertidos através do modelMapper para um objeto do tipo OperationResponse, se tornando assim,
            ** ideal para visualização do usuário */
            return ResponseEntity.created(uri)
                    .body(modelMapper.mapper().map(operationDAO.create(operationRequest), OperationResponse.class));

    }

}
