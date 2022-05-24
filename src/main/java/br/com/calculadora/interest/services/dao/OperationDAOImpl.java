package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationDAOImpl implements OperationDAO{

    @Autowired
    OperationRepository operationRepository;

    @Override
    public OperationEntity findById(Long id) {
        Optional<OperationEntity> optionalOperationEntity = operationRepository.findById(id);
        return optionalOperationEntity.orElse(null);
    }

}
