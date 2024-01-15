package com.incerti.asserts;

import com.incerti.adapter.persistence.ProdutoEntity;
import com.incerti.adapter.rest.response.ProdutoResponse;
import com.incerti.domain.model.Produto;
import lombok.experimental.UtilityClass;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@UtilityClass
public class ProdutoAsserts {

    public static void assertEntitySemId(Produto produto, ProdutoEntity entity) {
        assertThat(entity, is(notNullValue()));
        assertThat(entity.getCategoria(), equalTo(produto.getCategoria()));
        assertThat(entity.getNome(), equalTo(produto.getNome()));
        assertThat(entity.getPrecoBase(), equalTo(produto.getPrecoBase()));
        assertThat(entity.getPrecoTarifado(), equalTo(produto.getPrecoTarifado()));
    }

    public static void assertProduto(ProdutoEntity entity, Produto produto) {
        assertThat(produto, is(notNullValue()));
        assertThat(produto.getId(), is(entity.getId()));
        assertThat(produto.getCategoria(), equalTo(entity.getCategoria()));
        assertThat(produto.getNome(), equalTo(entity.getNome()));
        assertThat(produto.getPrecoBase(), equalTo(entity.getPrecoBase()));
        assertThat(produto.getPrecoTarifado(), equalTo(entity.getPrecoTarifado()));
    }

    public static void assertProduto(Produto esperado, Produto real) {
        assertThat(real, is(notNullValue()));
        assertThat(real.getId(), is(esperado.getId()));
        assertThat(real.getCategoria(), equalTo(esperado.getCategoria()));
        assertThat(real.getNome(), equalTo(esperado.getNome()));
        assertThat(real.getPrecoBase(), equalTo(esperado.getPrecoBase()));
        assertThat(real.getPrecoTarifado(), equalTo(esperado.getPrecoTarifado()));
    }

    public static void assertProdutoResponse(Produto produto, ProdutoResponse response) {
        assertThat(response, is(notNullValue()));
        assertThat(response.getId(), is(produto.getId()));
        assertThat(response.getCategoria(), equalTo(produto.getCategoria()));
        assertThat(response.getNome(), equalTo(produto.getNome()));
        assertThat(response.getPrecoBase(), equalTo(produto.getPrecoBase()));
        assertThat(response.getPrecoTarifado(), equalTo(produto.getPrecoTarifado()));
    }

    public static void assertProdutoResponseSucesso(Produto produto, ProdutoResponse response) {
        assertThat(response, is(notNullValue()));
        assertThat(response.getId(), is(notNullValue()));
        assertThat(response.getCategoria(), equalTo(produto.getCategoria()));
        assertThat(response.getNome(), equalTo(produto.getNome()));
        assertThat(response.getPrecoBase(), equalTo(produto.getPrecoBase()));
        assertThat(response.getPrecoTarifado(), is(notNullValue()));
    }
}
