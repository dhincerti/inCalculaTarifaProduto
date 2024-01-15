package com.incerti.integration;

import com.incerti.CalculaTarifasApplicationRunner;
import com.incerti.adapter.rest.controller.ProdutoController;
import com.incerti.adapter.rest.request.ProdutoRequest;
import com.incerti.adapter.mappers.ProdutoRequestMapper;
import com.incerti.adapter.rest.response.ProdutoResponse;
import com.incerti.domain.exception.BusinessException;
import com.incerti.domain.model.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.incerti.asserts.ProdutoAsserts.assertProdutoResponseSucesso;
import static com.incerti.mocks.ProdutoMock.umProduto;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CalculaTarifasApplicationRunner.class })
public class ProdutoCrudIT {

    @Autowired
    private ProdutoController controller;

    @Test
    void deveCriarProdutoComSucesso() {
        Produto produto = umProduto();
        ProdutoRequest request = ProdutoRequestMapper.map(produto);

        ResponseEntity<ProdutoResponse> responseEntity = controller.criaProduto(request);

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(201)));
        assertProdutoResponseSucesso(produto, responseEntity.getBody());
    }

    @Test
    void deveConsultarProdutoComSucesso() {
        Produto produto = umProduto();
        ProdutoRequest request = ProdutoRequestMapper.map(produto);

        ResponseEntity<ProdutoResponse> responseEntity = controller.criaProduto(request);

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(201)));
        assertProdutoResponseSucesso(produto, responseEntity.getBody());

        responseEntity = controller.consultaProduto(responseEntity.getBody().getId());

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertProdutoResponseSucesso(produto, responseEntity.getBody());
    }

    @Test
    void deveAlterarProdutoComSucesso() {
        Produto produto = umProduto();
        ProdutoRequest request = ProdutoRequestMapper.map(produto);

        ResponseEntity<ProdutoResponse> responseEntity = controller.criaProduto(request);

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(201)));
        assertProdutoResponseSucesso(produto, responseEntity.getBody());

        responseEntity = controller.atualizaProduto(responseEntity.getBody().getId(), request);

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertProdutoResponseSucesso(produto, responseEntity.getBody());
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        Produto produto = umProduto();
        ProdutoRequest request = ProdutoRequestMapper.map(produto);

        ResponseEntity<ProdutoResponse> responseCriacao = controller.criaProduto(request);

        assertThat(responseCriacao, is(notNullValue()));
        assertThat(responseCriacao.getStatusCode(), is(HttpStatusCode.valueOf(201)));
        assertProdutoResponseSucesso(produto, responseCriacao.getBody());

        ResponseEntity<Void> responseDelecao = controller.deletaProduto(responseCriacao.getBody().getId());

        assertThat(responseDelecao, is(notNullValue()));
        assertThat(responseDelecao.getStatusCode(), is(HttpStatusCode.valueOf(204)));

        assertThrows(BusinessException.class, () -> controller.consultaProduto(responseCriacao.getBody().getId()));
    }

}
