package com.incerti.adapter.mappers;

import com.incerti.adapter.datastorage.ProdutoEntity;
import com.incerti.domain.model.Produto;
import lombok.experimental.UtilityClass;

import java.util.UUID;

import static java.util.Objects.isNull;

@UtilityClass
public class ProdutoEntityMapper {

    public static ProdutoEntity map(UUID id, Produto produto) {
        if (isNull(produto))
            return new ProdutoEntity();

        return ProdutoEntity.builder()
                .id(id)
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .precoBase(produto.getPrecoBase())
                .precoTarifado(produto.getPrecoTarifado())
                .build();
    }

    public static ProdutoEntity map(Produto produto) {
        if (isNull(produto))
            return new ProdutoEntity();

        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .precoBase(produto.getPrecoBase())
                .precoTarifado(produto.getPrecoTarifado())
                .build();
    }

    public static Produto map(ProdutoEntity entity) {
        if (isNull(entity))
            return new Produto();

        return Produto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .categoria(entity.getCategoria())
                .precoBase(entity.getPrecoBase())
                .precoTarifado(entity.getPrecoTarifado())
                .build();
    }
}
