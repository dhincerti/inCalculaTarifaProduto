package com.incerti.aplication.usecases;

import com.incerti.aplication.repository.TarifasRepository;
import com.incerti.domain.model.Produto;
import com.incerti.domain.model.Tarifa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class TarifasUseCase {
    private final TarifasRepository inMemoryTarifasRepository;

    public BigDecimal calculaPrecoTarifado(Produto produto) {
        return inMemoryTarifasRepository.getTarifas(produto.getCategoria())
                .stream()
                .map(Tarifa::getValor)
                .reduce(produto.getPrecoBase(),
                        (total, valorTarifa) -> total.add(produto.getPrecoBase().multiply(valorTarifa)))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
