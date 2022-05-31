package br.com.calculadora.interest.services.dao;

import br.com.calculadora.interest.models.requests.OperationRequest;
import br.com.calculadora.interest.models.entities.OperationEntity;

import java.util.List;

/** Interface utilizada para conter o acesso aos métodos do repositório de acesso ao banco de dados
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @email gabriellagrota23@gmail.com
 ** @since 31/05/2022 */
public interface OperationDAO {

    List<OperationEntity> findAll();
    OperationEntity findById(Long id);
    OperationEntity create(OperationRequest operationRequest);

}
