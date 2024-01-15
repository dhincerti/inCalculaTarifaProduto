package com.incerti.adapter.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.incerti.domain.model.type.Categoria;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProdutoResponse {
    private UUID id;
    private String nome;
    private Categoria categoria;

    @JsonProperty("preco_base")
    private BigDecimal precoBase;

    @JsonProperty("preco_tarifado")
    private BigDecimal precoTarifado;
}
