package com.incerti.adapter.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.incerti.domain.model.type.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
    private String nome;
    private Categoria categoria;

    @JsonProperty("preco_base")
    private BigDecimal precoBase;
}
