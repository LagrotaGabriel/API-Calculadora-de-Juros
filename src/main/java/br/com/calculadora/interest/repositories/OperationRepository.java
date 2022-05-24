package br.com.calculadora.interest.repositories;

import br.com.calculadora.interest.models.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

}
