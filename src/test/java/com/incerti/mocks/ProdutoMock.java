package com.incerti.mocks;

import com.incerti.domain.model.Produto;
import com.incerti.domain.model.type.Categoria;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static com.incerti.utils.CategoriaUtils.umaCategoria;
import static com.incerti.utils.PrecoUtils.umPreco;

@UtilityClass
public class ProdutoMock {

    public static Produto umProduto() {
        return umProduto(umaCategoria());
    }

    public static Produto umProduto(Categoria categoria) {
        return umProduto(UUID.randomUUID(), categoria, umPreco());
    }

    public static Produto umProduto(Categoria categoria, BigDecimal precoBase) {
        return umProduto(UUID.randomUUID(), categoria, precoBase);
    }

    public static Produto umProduto(UUID id, Categoria categoria, BigDecimal precoBase) {

        return Produto.builder()
                .id(id)
                .categoria(categoria)
                .nome("Produto " + id)
                .precoBase(precoBase.setScale(2, RoundingMode.HALF_UP))
                .precoTarifado(umPreco())
                .build();
    }
}
