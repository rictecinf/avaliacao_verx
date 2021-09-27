package br.com.verex.avaliacao.controller.converter.impl;

import br.com.verex.avaliacao.controller.converter.PedidoConverter;
import br.com.verex.avaliacao.controller.shared.VencimentoPedidoVO;
import br.com.verex.avaliacao.controller.shared.PedidoVO;
import br.com.verex.avaliacao.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoConverterImpl implements PedidoConverter {

    @Override
    public PedidoVO converterPedidoEntityToVO(PedidoEntity pedidoEntity) {
            return PedidoVO.builder()
                .id(pedidoEntity.getId())
                .dataCadastro(pedidoEntity.getDataCriacao())
                .dataVencimento(pedidoEntity.getDataVencimento())
                .status(Objects.isNull(pedidoEntity.getStatusPedidoEnum()) ? null : pedidoEntity.getStatusPedidoEnum().name())
                .build();
    }

    @Override
    public PedidoEntity convertPedidoVOToEntity(PedidoVO pedidoVO) {
        return PedidoEntity.builder()
                .id(pedidoVO.getId())
                .dataCriacao(Optional.ofNullable(pedidoVO.getDataCadastro()).orElse(null))
                .dataVencimento(pedidoVO.getDataVencimento())
                .build();
    }

    @Override
    public PedidoEntity convertPedidoVOToEntity(Optional<String> idPedido, PedidoVO pedidoVO) {
        return PedidoEntity.builder()
                .id(idPedido.orElse(pedidoVO.getId()))
                .dataCriacao(Optional.ofNullable(pedidoVO.getDataCadastro()).orElse(null))
                .dataVencimento(pedidoVO.getDataVencimento())
                .build();
    }

    @Override
    public PedidoEntity convertVencimentoVoToEntity(String idPedido, VencimentoPedidoVO vencimentoPedidoDto) {
        return PedidoEntity
                .builder()
                .id(idPedido)
                .dataVencimento(vencimentoPedidoDto.getDataVencimento())
                .build();
    }

    @Override
    public Page<PedidoVO> convertPageToVOList(Page<PedidoEntity> listar) {
        final List<PedidoVO> pedidoVOS = listar.stream()
                .map(pedido -> converterPedidoEntityToVO(pedido))
                .collect(Collectors.toList());

        return new PageImpl<>(pedidoVOS,listar.getPageable(),listar.getTotalElements());
    }

}
