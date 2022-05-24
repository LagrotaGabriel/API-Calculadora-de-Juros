package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.models.entities.OperationEntity;

public interface OperationDAO {

    OperationEntity findById(Long id);
}
