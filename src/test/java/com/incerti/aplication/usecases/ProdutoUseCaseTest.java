package com.incerti.aplication.usecases;

import com.incerti.aplication.repository.ProdutoRepository;
import com.incerti.domain.exception.SystemException;
import com.incerti.domain.model.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.incerti.asserts.ProdutoAsserts.assertProduto;
import static com.incerti.mocks.ProdutoMock.umProduto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoUseCaseTest {

    private final ProdutoRepository repository = mock(ProdutoRepository.class);
    private final TarifasUseCase tarifasUseCase = mock(TarifasUseCase.class);

    private final ProdutoUseCase produtoUseCase = new ProdutoUseCase(repository, tarifasUseCase);

    @Test
    void deveConsultarProduto() {
        Produto produto = umProduto();

        when(repository.consulta(produto.getId())).thenReturn(produto);

        Produto produtoCriado = produtoUseCase.consultaProduto(produto.getId());

        assertProduto(produto, produtoCriado);
    }

    @Test
    void deveCriarProduto() {
        Produto produto = umProduto();

        when(tarifasUseCase.calculaPrecoTarifado(produto)).thenReturn(produto.getPrecoTarifado());
        when(repository.cria(produto)).thenReturn(produto);

        Produto produtoCriado = produtoUseCase.criaProduto(produto);

        assertProduto(produto, produtoCriado);
    }

    @Test
    void deveAtualizarProduto() {
        Produto produto = umProduto();

        when(tarifasUseCase.calculaPrecoTarifado(produto)).thenReturn(produto.getPrecoTarifado());
        when(repository.atualiza(produto)).thenReturn(produto);

        Produto produtoCriado = produtoUseCase.atualizaProduto(produto);

        assertProduto(produto, produtoCriado);
    }

    @Test
    void deveDeletarProduto() {
        Produto produto = umProduto();

        doNothing().when(repository).deleta(produto.getId());

        produtoUseCase.deletaProduto(produto.getId());

        verify(repository).deleta(produto.getId());
    }

    @Test
    void deveFalharAoConsultarProduto() {
        doThrow(RuntimeException.class).when(repository).consulta(any());

        assertThrows(SystemException.class, () -> produtoUseCase.consultaProduto(UUID.randomUUID()));
    }

    @Test
    void deveFalharAoCriarProduto() {
        doThrow(RuntimeException.class).when(repository).cria(any());

        assertThrows(SystemException.class, () -> produtoUseCase.criaProduto(umProduto()));
    }

    @Test
    void deveFalharAoAtualizarProduto() {
        doThrow(RuntimeException.class).when(repository).atualiza(any());

        assertThrows(SystemException.class, () -> produtoUseCase.atualizaProduto(umProduto()));
    }

    @Test
    void deveFalharAoDeletarProduto() {
        doThrow(RuntimeException.class).when(repository).deleta(any());

        assertThrows(SystemException.class, () -> produtoUseCase.deletaProduto(UUID.randomUUID()));
    }
}