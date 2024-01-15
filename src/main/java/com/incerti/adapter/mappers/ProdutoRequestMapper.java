package com.incerti.adapter.mappers;

import com.incerti.adapter.rest.request.ProdutoRequest;
import com.incerti.domain.model.Produto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProdutoRequestMapper {

    public static Produto map(ProdutoRequest request) {
        return Produto.builder()
                .nome(request.getNome())
                .categoria(request.getCategoria())
                .precoBase(request.getPrecoBase())
                .build();
    }

    public static ProdutoRequest map(Produto produto) {
        return ProdutoRequest.builder()
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .precoBase(produto.getPrecoBase())
                .build();
    }
}
