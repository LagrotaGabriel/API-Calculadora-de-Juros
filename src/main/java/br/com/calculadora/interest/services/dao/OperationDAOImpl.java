package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.resources.exceptions.ObjectNotFoundException;
import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Classe de serviço para implementação e tratamento de persistência de dados
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 */
@Service
public class OperationDAOImpl implements OperationDAO{

    // Injeção de dependência da interface OperationRepository, que fornece os métodos de persistência do JPA
    @Autowired
    OperationRepository operationRepository;

    // Injeção de dependência da classe de configuração ModelMapperConfig, que permite realizar a conversão de objetos
    @Autowired
    ModelMapperConfig modelMapper;

    /** Método que fornece todos os objetos do tipo OperationEntity cadastrados no banco de dados
     ** @return Retorna todos os objetos do tipo OperationEntity cadastrados no banco de dados */
    @Override
    public List<OperationEntity> findAll() {
        return operationRepository.findAll();
    }

    /** Método que fornece objeto do tipo OperationEntity através do id na qual é passado pelo seu parâmetro
     ** @param id Recebe um id do tipo Long, que é usado para buscar por um OperationEntity pelo id
     ** @see ObjectNotFoundException Lança uma excessão do tipo ObjectNotFoundException caso nenhum OperationEntity seja encontrado
     ** @return Retorna um objeto do tipo OperationEntity, caso encontrado */
    @Override
    public OperationEntity findById(Long id) {
        Optional<OperationEntity> optionalOperationEntity = operationRepository.findById(id);
        return optionalOperationEntity.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    /** Método que realiza a persistência de um objeto do tipo OperationEntity no banco de dados com base no
     ** parâmetro recebido do tipo OperationRequest
     ** @param operationRequest Recebe como parâmetro um objeto do tipo OperationRequest, contendo os atributos
     **                         utilizados na persistência do objeto
     ** @see ModelMapperConfig Realiza a conversão do objeto recebido no parâmetro OperationRequest para um objeto
     ** do tipo OperationEntity através do modelMapper
     ** @return Retorna um objeto do tipo OperationEntity, após conversão e persistência do objeto*/
    @Override
    public OperationEntity create(OperationRequest operationRequest) {
        return operationRepository.save(modelMapper.mapper().map(operationRequest, OperationEntity.class));
    }


}
