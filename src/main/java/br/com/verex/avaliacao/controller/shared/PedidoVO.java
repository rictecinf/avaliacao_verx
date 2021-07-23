package br.com.verex.avaliacao.controller.shared;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class PedidoVO {

    private String id;
    private LocalDateTime dataCadastro;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataVencimento;
    private String status;

}
