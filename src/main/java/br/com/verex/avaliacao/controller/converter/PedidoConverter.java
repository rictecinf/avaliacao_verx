package br.com.verex.avaliacao.controller.converter;

import br.com.verex.avaliacao.controller.shared.VencimentoPedidoVO;
import br.com.verex.avaliacao.controller.shared.PedidoVO;
import br.com.verex.avaliacao.entity.PedidoEntity;
import org.springframework.data.domain.Page;

public interface PedidoConverter {

    PedidoVO converterPedidoEntityToVO(PedidoEntity pedidoEntity);

    PedidoEntity convertPedidoVOToEntity(PedidoVO pedidoVO);

    PedidoEntity convertVencimentoVoToEntity(String idPedido, VencimentoPedidoVO vencimentoPedidoDto);

    Page<PedidoVO> convertPageToVOList(Page<PedidoEntity> listar);
}
