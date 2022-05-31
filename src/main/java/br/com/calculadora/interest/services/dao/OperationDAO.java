package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.models.entities.OperationEntity;

import java.util.List;

public interface OperationDAO {

    List<OperationEntity> findAll();
    OperationEntity findById(Long id);
    OperationEntity create(OperationRequest operationRequest);

}
