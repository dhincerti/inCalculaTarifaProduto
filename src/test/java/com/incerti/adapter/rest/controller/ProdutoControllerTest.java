package com.incerti.adapter.rest.controller;

import com.incerti.adapter.rest.request.ProdutoRequest;
import com.incerti.adapter.mappers.ProdutoRequestMapper;
import com.incerti.adapter.rest.response.ProdutoResponse;
import com.incerti.aplication.usecases.ProdutoUseCase;
import com.incerti.domain.model.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static com.incerti.asserts.ProdutoAsserts.assertProdutoResponse;
import static com.incerti.mocks.ProdutoMock.umProduto;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    private final ProdutoUseCase useCase = mock(ProdutoUseCase.class);
    private final ProdutoController controller = new ProdutoController(useCase);

    @Test
    void deveConsultarProdutoComSucesso() {
        Produto produto = umProduto();

        when(useCase.consultaProduto(produto.getId())).thenReturn(produto);

        ResponseEntity<ProdutoResponse> responseEntity = controller.consultaProduto(produto.getId());

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertProdutoResponse(produto, responseEntity.getBody());
    }

    @Test
    void deveCriarProdutoComSucesso() {
        Produto produto = umProduto();
        ProdutoRequest request = ProdutoRequestMapper.map(produto);

        when(useCase.criaProduto(any())).thenReturn(produto);

        ResponseEntity<ProdutoResponse> responseEntity = controller.criaProduto(request);

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(201)));
        assertProdutoResponse(produto, responseEntity.getBody());
    }

    @Test
    void deveAlterarProdutoComSucesso() {
        Produto produto = umProduto();
        ProdutoRequest request = ProdutoRequestMapper.map(produto);

        when(useCase.atualizaProduto(any())).thenReturn(produto);

        ResponseEntity<ProdutoResponse> responseEntity = controller.atualizaProduto(produto.getId(), request);

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertProdutoResponse(produto, responseEntity.getBody());
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        Produto produto = umProduto();

        when(useCase.atualizaProduto(any())).thenReturn(produto);

        ResponseEntity<Void> responseEntity = controller.deletaProduto(produto.getId());

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatusCode.valueOf(204)));
    }
}