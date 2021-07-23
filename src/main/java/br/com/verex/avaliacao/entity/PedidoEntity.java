package br.com.verex.avaliacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@NamedEntityGraph(name = "pedido-item-graph", attributeNodes = @NamedAttributeNode(value = "itens"))
public class PedidoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private LocalDateTime dataCriacao;
    @Builder.Default
    @OneToMany(mappedBy = "pedidoEntity")
    private List<ItemPedidoEntity> itens = new ArrayList<>();
    private StatusPedidoEnum statusPedidoEnum;
    private LocalDate dataVencimento;

    public enum StatusPedidoEnum{
        INATIVO(0,"INATIVO"),
        ATIVO(1, "ATIVO");

        private String descricao;
        private Integer id;

        StatusPedidoEnum(Integer id, String descricao) {
            this.id = id;
            this.descricao = descricao;

        }

        public static PedidoEntity.StatusPedidoEnum fromId(Integer id) {
            if (Objects.isNull(id)) {
                return null;
            }
            return Arrays.stream(values())
                    .filter(item-> item.id == id)
                    .findFirst().orElse(null);
        }

    }
}
