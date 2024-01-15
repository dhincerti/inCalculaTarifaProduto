package com.incerti.integration;

import com.incerti.CalculaTarifasApplicationRunner;
import com.incerti.adapter.datastorage.H2ProdutoRepository;
import com.incerti.adapter.datastorage.ProdutoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.incerti.mocks.ProdutoEntityMock.umProdutoEntity;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CalculaTarifasApplicationRunner.class })
class H2ProdutoRepositoryIT {

    @Autowired
    private H2ProdutoRepository repository;

    @Test
    void deveCriarProduto() {
        ProdutoEntity entity = umProdutoEntity();

        ProdutoEntity produtoSalvo = repository.save(entity);

        assertThat(produtoSalvo, is(notNullValue()));
    }

    @Test
    void deveConsultarProduto() {
        ProdutoEntity entity = umProdutoEntity();

        ProdutoEntity produtoSalvo = repository.save(entity);
        assertThat(produtoSalvo, is(notNullValue()));

        ProdutoEntity produtoConsultado = repository.findById(produtoSalvo.getId()).orElse(null);
        assertThat(produtoConsultado, is(notNullValue()));
    }

    @Test
    void deveDeletarProduto() {
        ProdutoEntity entity = umProdutoEntity();

        ProdutoEntity produtoSalvo = repository.save(entity);
        assertThat(produtoSalvo, is(notNullValue()));

        repository.deleteById(produtoSalvo.getId());
        ProdutoEntity produtoConsultado = repository.findById(produtoSalvo.getId()).orElse(null);

        assertThat(produtoConsultado, is(nullValue()));
    }
}