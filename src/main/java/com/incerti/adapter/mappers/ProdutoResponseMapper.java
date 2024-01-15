package com.incerti.adapter.mappers;

import com.incerti.adapter.rest.response.ProdutoResponse;
import com.incerti.domain.model.Produto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProdutoResponseMapper {

    public static ProdutoResponse map(Produto produto) {
        return ProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .precoBase(produto.getPrecoBase())
                .precoTarifado(produto.getPrecoTarifado())
                .build();
    }
}
