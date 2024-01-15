package com.incerti.adapter.datastorage;

import com.incerti.adapter.mappers.ProdutoEntityMapper;
import com.incerti.asserts.ProdutoAsserts;
import com.incerti.domain.model.Produto;
import com.incerti.mocks.ProdutoEntityMock;
import com.incerti.mocks.ProdutoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.incerti.asserts.ProdutoAsserts.assertProduto;
import static com.incerti.mocks.ProdutoEntityMock.umProdutoEntity;
import static com.incerti.mocks.ProdutoMock.umProduto;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class ProdutoEntityMapperTest {

    @Test
    void deveMapearDeProdutoParaEntidadeComSucesso() {
        Produto produto = ProdutoMock.umProduto();

        ProdutoEntity entity = ProdutoEntityMapper.map(produto);

        ProdutoAsserts.assertEntitySemId(produto, entity);
    }

    @Test
    void deveMapearDeProdutoParaEntidadeComSucessoQuandoProdutoForNulo() {
        Produto produto = null;

        ProdutoEntity entity = ProdutoEntityMapper.map(produto);

        assertThat(entity, is(notNullValue()));
    }

    @Test
    void deveMapearDeProdutoParaEntidadeComIdComSucesso() {
        Produto produto = ProdutoMock.umProduto();

        ProdutoEntity entity = ProdutoEntityMapper.map(produto.getId(), produto);

        ProdutoAsserts.assertEntitySemId(produto, entity);
    }

    @Test
    void deveMapearDeProdutoParaEntidadeComIdComSucessoQuandoProdutoForNulo() {
        Produto produto = null;

        ProdutoEntity entity = ProdutoEntityMapper.map(UUID.randomUUID(), produto);

        assertThat(entity, is(notNullValue()));
    }

    @Test
    void deveMapearDeEntidadeParaProdutoComSucesso() {
        ProdutoEntity entity = ProdutoEntityMock.umProdutoEntity();

        Produto produto = ProdutoEntityMapper.map(entity);

        ProdutoAsserts.assertProduto(entity, produto);
    }

    @Test
    void deveMapearDeEntidadeParaProdutoComSucessoQuandoEntidadeForNula() {
        ProdutoEntity entity = null;

        Produto produto = ProdutoEntityMapper.map(entity);

        assertThat(entity, is(nullValue()));
    }
}