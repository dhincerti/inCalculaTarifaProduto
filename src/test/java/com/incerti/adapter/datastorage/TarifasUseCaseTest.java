package com.incerti.adapter.datastorage;

import com.incerti.aplication.repository.TarifasRepository;
import com.incerti.aplication.usecases.TarifasUseCase;
import com.incerti.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.incerti.domain.model.type.Categoria.*;
import static com.incerti.mocks.ProdutoMock.umProduto;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class TarifasUseCaseTest {
    private final TarifasRepository inMemoryTarifasRepository = new InMemoryTarifasRepository();
    private final TarifasUseCase useCase = new TarifasUseCase(inMemoryTarifasRepository);

    @Test
    void deveCaluclarPrecoTarifadoDeSeguroDeVidaComSucesso() {
        Produto produto = umProduto(VIDA, new BigDecimal("100"));

        BigDecimal precoTarifado = useCase.calculaPrecoTarifado(produto);

        assertThat(precoTarifado, equalTo(new BigDecimal("103.20")));
    }

    @Test
    void deveCaluclarPrecoTarifadoDeSeguroAutoComSucesso() {
        Produto produto = umProduto(AUTO, new BigDecimal("50.0000"));

        BigDecimal precoTarifado = useCase.calculaPrecoTarifado(produto);

        assertThat(precoTarifado, equalTo(new BigDecimal("55.25")));
    }

    @Test
    void deveCaluclarPrecoTarifadoDeSeguroViagemComSucesso() {
        Produto produto = umProduto(VIAGEM, new BigDecimal("120"));

        BigDecimal precoTarifado = useCase.calculaPrecoTarifado(produto);

        assertThat(precoTarifado, equalTo(new BigDecimal("128.40")));
    }

    @Test
    void deveCaluclarPrecoTarifadoDeSeguroResidencialComSucesso() {
        Produto produto = umProduto(RESIDENCIAL, new BigDecimal("44.44"));

        BigDecimal precoTarifado = useCase.calculaPrecoTarifado(produto);

        assertThat(precoTarifado, equalTo(new BigDecimal("47.55")));
    }

    @Test
    void deveCaluclarPrecoTarifadoDeSeguroPatrimonialComSucesso() {
        Produto produto = umProduto(PATRIMONIAL, new BigDecimal("18765.28"));

        BigDecimal precoTarifado = useCase.calculaPrecoTarifado(produto);

        assertThat(precoTarifado, equalTo(new BigDecimal("20266.50")));
    }
}