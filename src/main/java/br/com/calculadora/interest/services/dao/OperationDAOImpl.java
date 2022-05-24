package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.config.ModelMapperConfig;
import br.com.calculadora.interest.exceptions.ObjectNotFoundException;
import br.com.calculadora.interest.models.dto.OperationDTO;
import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationDAOImpl implements OperationDAO{

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    @Override
    public List<OperationEntity> findAll() {
        return operationRepository.findAll();
    }

    @Override
    public OperationEntity findById(Long id) {
        Optional<OperationEntity> optionalOperationEntity = operationRepository.findById(id);
        return optionalOperationEntity.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public OperationEntity create(OperationDTO operationDTO) {
        return operationRepository.save(modelMapper.mapper().map(operationDTO, OperationEntity.class));
    }


}
