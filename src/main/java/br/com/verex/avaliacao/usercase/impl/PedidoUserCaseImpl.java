package br.com.verex.avaliacao.usercase.impl;

import br.com.verex.avaliacao.controller.shared.PedidoFilter;
import br.com.verex.avaliacao.entity.DomainException;
import br.com.verex.avaliacao.entity.PedidoEntity;
import br.com.verex.avaliacao.gateway.PedidoGateway;
import br.com.verex.avaliacao.usercase.PedidoUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PedidoUserCaseImpl implements PedidoUserCase {

    private final PedidoGateway pedidoGaeteway;

    @Override
    public Optional<PedidoEntity> obterPedidoPorId(String idPedido) {
        return pedidoGaeteway.findById(idPedido);
    }

    @Override
    public PedidoEntity criar(PedidoEntity pedidoEntity) {
        return pedidoGaeteway.save(pedidoEntity.toBuilder()
                .statusPedidoEnum(PedidoEntity.StatusPedidoEnum.ATIVO)
                .build()
        );
    }

    @Override
    public PedidoEntity atualizarVencimento(PedidoEntity pedidoEntity) throws DomainException {
        final PedidoEntity pedidoDB = pedidoGaeteway.findById(pedidoEntity.getId())
                .orElseThrow(() -> new DomainException("Pedido não foi encontrado"));

        return pedidoGaeteway.save(pedidoDB.toBuilder()
                .dataVencimento(pedidoEntity.getDataVencimento())
                .build()
        );
    }

    @Override
    public PedidoEntity atualizarStatus(String idPedido, String status) throws DomainException {
        final PedidoEntity pedidoEntity = pedidoGaeteway.findById(idPedido)
                .orElseThrow(() -> new DomainException("Pedido não foi encontrado"));
        return pedidoGaeteway.save(pedidoEntity.toBuilder()
                .statusPedidoEnum(PedidoEntity.StatusPedidoEnum.fromId(Integer.valueOf(status)))
                .build()
        );
    }


    @Override
    public Page<PedidoEntity> listar(PedidoFilter filtro, int pagina, int tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, Sort.by(Sort.Direction.DESC, "dataCriacao"));
        return pedidoGaeteway.findAll(pageRequest);
    }

    @Override
    public void excluir(PedidoEntity pedidoEntity) {
        pedidoEntity.toBuilder().statusPedidoEnum(PedidoEntity.StatusPedidoEnum.INATIVO).build();
        pedidoGaeteway.save(pedidoEntity.toBuilder()
                .statusPedidoEnum(PedidoEntity.StatusPedidoEnum.INATIVO)
                .build()
        );
    }
}
