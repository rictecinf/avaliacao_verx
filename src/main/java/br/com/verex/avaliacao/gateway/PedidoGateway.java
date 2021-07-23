package br.com.verex.avaliacao.gateway;

import br.com.verex.avaliacao.entity.PedidoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PedidoGateway extends JpaRepository<PedidoEntity,String> {

    @EntityGraph( type = EntityGraph.EntityGraphType.FETCH, attributePaths = "itens")
    Optional<PedidoEntity> findById(String id);


}
