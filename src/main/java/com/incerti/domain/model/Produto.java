package com.incerti.domain.model;

import com.incerti.domain.model.type.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private UUID id;
    private String nome;
    private Categoria categoria;
    private BigDecimal precoBase;
    private BigDecimal precoTarifado;
}
