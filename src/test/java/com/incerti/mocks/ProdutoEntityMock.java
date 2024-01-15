package com.incerti.mocks;

import com.incerti.adapter.datastorage.ProdutoEntity;
import com.incerti.utils.CategoriaUtils;
import com.incerti.utils.PrecoUtils;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class ProdutoEntityMock {

    public ProdutoEntity umProdutoEntity() {
        UUID id = UUID.randomUUID();
        return umProdutoEntity(id);
    }

    public static ProdutoEntity umProdutoEntity(UUID id) {

        return ProdutoEntity.builder()
                .id(id)
                .categoria(CategoriaUtils.umaCategoria())
                .nome("Produto " + id)
                .precoBase(PrecoUtils.umPreco())
                .precoTarifado(PrecoUtils.umPreco())
                .build();
    }
}
