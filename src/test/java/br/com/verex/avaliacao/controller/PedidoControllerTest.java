package br.com.verex.avaliacao.controller;

import br.com.verex.avaliacao.controller.converter.PedidoConverter;
import br.com.verex.avaliacao.controller.shared.PedidoVO;
import br.com.verex.avaliacao.usercase.PedidoUserCase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoUserCase pedidoUserCase;

    @MockBean
    private PedidoConverter pedidoConverter;


    private final PedidoVO pedidoVO =
            PedidoVO.builder()
                    .id("5a1659a3-d99e-4112-8f00-1c36583e821c")
                    .dataCadastro(LocalDateTime.now())
                    .dataVencimento(LocalDate.now())
                    .status("0")
                    .build();

    @Test
    @DisplayName("Deveria criar pedido, retornando successful")
    void criar() throws Exception {

        final PedidoVO pedidoVO =
                PedidoVO.builder()
                        .id("5a1659a3-d99e-4112-8f00-1c36583e821c")
                        .dataCadastro(LocalDateTime.now())
                        .dataVencimento(LocalDate.now())
                        .build();
        this.mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(pedidoVO)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Deveria lançar BAD_REQUEST, ao tentar recuperar pedido")
    void consultar() throws Exception {
         this.mockMvc.perform(get("/pedidos/5a1659a3-d99e-4112-8f00-1c36583e821c"))
                .andDo(print())
                .andExpect(jsonPath("$.error", is("pedido não encontrado")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listar() throws Exception {

        this.mockMvc.perform(get("/pedidos?pagina=0&tamanhoPagina=10"))
                .andDo(print()).andExpect(status().isOk());
    }



    //@Test
    void alterar() throws Exception {


        this.mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(pedidoVO)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    //@Test
    void excluir() throws Exception {

        this.mockMvc.perform(delete("/pedidos/5a1659a3-d99e-4112-8f00-1c36583e821c")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(pedidoVO)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void atualizarStatus() {
    }

    @Test
    void atualizarVencimentoPedido() {
    }

    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}