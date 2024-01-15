package com.incerti.adapter.persistence;

import com.incerti.adapter.mappers.ProdutoEntityMapper;
import com.incerti.aplication.repository.ProdutoRepository;
import com.incerti.domain.exception.ProdutoNaoEncontratoException;
import com.incerti.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.incerti.adapter.mappers.ProdutoEntityMapper.map;
import static com.incerti.mocks.ProdutoEntityMock.umProdutoEntity;
import static com.incerti.mocks.ProdutoMock.umProduto;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ProdutoRepositoryImplTest {

    private final H2ProdutoRepository h2Repository = mock(H2ProdutoRepository.class);
    private final ProdutoRepository produtoRepository = new ProdutoRepositoryImpl(h2Repository);

    @Test
    void deveCriarProdutoComSucesso() {
        Produto produtoEsperado = umProduto();
        ProdutoEntity entity = ProdutoEntityMapper.map(produtoEsperado.getId(), produtoEsperado);

        when(h2Repository.save(any())).thenReturn(entity);

        Produto produtoReal = produtoRepository.cria(produtoEsperado);

        assertThat(produtoReal, Matchers.samePropertyValuesAs(produtoEsperado));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produtoEsperado = umProduto();
        ProdutoEntity entity = ProdutoEntityMapper.map(produtoEsperado.getId(), produtoEsperado);

        when(h2Repository.findById(produtoEsperado.getId())).thenReturn(Optional.of(entity));
        when(h2Repository.save(any())).thenReturn(entity);

        Produto produtoReal = produtoRepository.atualiza(produtoEsperado);

        assertThat(produtoReal, Matchers.samePropertyValuesAs(produtoEsperado));
    }

    @Test
    void deveFalharAoAtualizarProdutoNaoExistente() {
        Produto produtoEsperado = umProduto();

        assertThrows(ProdutoNaoEncontratoException.class, () -> produtoRepository.atualiza(produtoEsperado));
    }

    @Test
    void deveConsultarProdutoComSucesso() {
        UUID id = UUID.randomUUID();
        ProdutoEntity entity = umProdutoEntity(id);

        when(h2Repository.findById(id)).thenReturn(Optional.of(entity));

        Produto produtoReal = produtoRepository.consulta(id);

        assertThat(produtoReal, is(notNullValue()));
        assertThat(produtoReal.getId(), is(notNullValue()));
        assertThat(produtoReal.getCategoria(), is(notNullValue()));
        assertThat(produtoReal.getNome(), is(notNullValue()));
        assertThat(produtoReal.getPrecoBase(), is(notNullValue()));
        assertThat(produtoReal.getPrecoTarifado(), is(notNullValue()));
    }

    @Test
    void deveFalharAoConsultarProdutoNaoExistente() {
        Produto produtoEsperado = umProduto();

        assertThrows(ProdutoNaoEncontratoException.class, () -> produtoRepository.consulta(produtoEsperado.getId()));
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        UUID id = UUID.randomUUID();

        produtoRepository.deleta(id);

        verify(h2Repository).deleteById(id);
    }
}