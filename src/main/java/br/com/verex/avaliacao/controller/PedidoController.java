package br.com.verex.avaliacao.controller;

import br.com.verex.avaliacao.controller.converter.PedidoConverter;
import br.com.verex.avaliacao.controller.shared.PedidoFilter;
import br.com.verex.avaliacao.controller.shared.PedidoVO;
import br.com.verex.avaliacao.controller.shared.ServiceResponse;
import br.com.verex.avaliacao.controller.shared.VencimentoPedidoVO;
import br.com.verex.avaliacao.entity.DomainException;
import br.com.verex.avaliacao.entity.PedidoEntity;
import br.com.verex.avaliacao.usercase.PedidoUserCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Validated
@RestController
@RequestMapping("pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoUserCase pedidoUserCase;
    private final PedidoConverter pedidoConverter;

    @GetMapping("/{idPedido}")
    public ResponseEntity<ServiceResponse<PedidoVO>> consultar(@PathVariable("idPedido") String idPedido) throws Exception {

        final PedidoEntity pedidoEntity = pedidoUserCase.obterPedidoPorId(idPedido).orElseThrow(()-> new DomainException("pedido não encontrado"));
        final PedidoVO pedidoVO = pedidoConverter.converterPedidoEntityToVO(pedidoEntity);
        return new ServiceResponse<PedidoVO>().okResponse(pedidoVO);
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<Page<PedidoVO>>> listar(PedidoFilter filtro , @RequestParam int pagina, @RequestParam(defaultValue = "10") int tamanhoPagina ) {
        final Page<PedidoEntity> listar = pedidoUserCase.listar(filtro, pagina, tamanhoPagina);
        listar.getContent()
                .stream()
                .collect(Collectors
                        .groupingBy(PedidoEntity::getDataVencimento,
                        collectingAndThen(toList(),
                                list->{
                                    final Long total = list.stream().collect(counting());
                                    return  total;
                                })));
        final Page<PedidoVO> pedidoVOS = pedidoConverter.convertPageToVOList(listar);

        return new ServiceResponse<Page<PedidoVO>>().okResponse(pedidoVOS);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<Void>> criar(@RequestBody PedidoVO pedido) {
        final PedidoEntity pedidoEntity = pedidoConverter.convertPedidoVOToEntity(pedido);
        pedidoUserCase.criar(pedidoEntity);
        //return new ServiceResponse<Void>().okOrNoContent(null);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<ServiceResponse<Void>> alterar(@PathVariable("idPedido") String idPedido, @RequestBody PedidoVO pedido) {

        final PedidoEntity pedidoEntity = pedidoConverter.convertPedidoVOToEntity(Optional.ofNullable(null) ,pedido);
        pedidoUserCase.atualizaPedido(pedidoEntity.toBuilder().id(idPedido).build());
        return ResponseEntity.noContent().build();
    }

    /*
        Exlusão lógica
    */

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<ServiceResponse<Void>> excluir(@PathVariable("idPedido") String idPedido, @RequestBody PedidoVO pedido) {

        final PedidoEntity pedidoEntity = pedidoConverter.convertPedidoVOToEntity(pedido);
        pedidoUserCase.excluir(pedidoEntity.toBuilder().id(idPedido).build());

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/atualizar-status/{idPedido}/{status}")
    public ResponseEntity<ServiceResponse<Void>> atualizarStatus(@PathVariable("idPedido")String idPedido, @PathVariable("status")String status) throws Exception {
        pedidoUserCase.atualizarStatus(idPedido,status);
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/atualizar-vencimento/{idPedido}")
    public ResponseEntity<ServiceResponse<Void>> atualizarVencimentoPedido(@PathVariable("idPedido") String idPedido, @RequestBody VencimentoPedidoVO vencimentoPedidoDto) throws Exception {
        final PedidoEntity pedidoEntity = pedidoConverter.convertVencimentoVoToEntity(idPedido, vencimentoPedidoDto);
        pedidoUserCase.atualizarVencimento(pedidoEntity);

        return ResponseEntity.noContent().build();

    }


}
