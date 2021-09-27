package br.com.verex.avaliacao.usercase;

import br.com.verex.avaliacao.controller.shared.PedidoFilter;
import br.com.verex.avaliacao.entity.DomainException;
import br.com.verex.avaliacao.entity.PedidoEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PedidoUserCase {

    Optional<PedidoEntity> obterPedidoPorId(String idPedido);

    PedidoEntity criar(PedidoEntity pedidoEntity);

    PedidoEntity atualizarVencimento(PedidoEntity pedidoEntity) throws DomainException;

    PedidoEntity atualizarStatus(String idPedido, String status) throws DomainException;

    Page<PedidoEntity> listar(PedidoFilter filtro, int pagina, int tamanhoPagina);

    void excluir(PedidoEntity pedidoEntity);

    void atualizaPedido(PedidoEntity pedidoEntity);
}
