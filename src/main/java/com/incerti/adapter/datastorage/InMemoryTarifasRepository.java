package com.incerti.adapter.datastorage;

import com.incerti.aplication.repository.TarifasRepository;
import com.incerti.domain.model.Tarifa;
import com.incerti.domain.model.type.Categoria;
import com.incerti.domain.model.type.TipoTarifa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Dado a observação sobre a não persistência e/ou parametrização dos valores das tarifas, descrito no desafio,
 * optei pela forma que me pareceu mais simples, sendo essa, a criação de um simples map.
 * <br/><br/>
 * A chave será a categoria, possibilitando recuperar os valores com um simples get(), retornando uma lista
 * de {@link Tarifa}
 *
 * @see <a href="https://github.com/itausegdev/backend-challenge?tab=readme-ov-file#observa%C3%A7%C3%B5es-importantes">Observações importantes</a>
 */
@Component
public class InMemoryTarifasRepository implements TarifasRepository {

    private final Map<Categoria, List<Tarifa>> tarifas = new HashMap<>();

    public InMemoryTarifasRepository() {
        carregaTarifas();
    }

    @Override
    public List<Tarifa> getTarifas(Categoria categoria) {
        return tarifas.get(categoria);
    }

    private void carregaTarifas() {
        tarifas.put(Categoria.VIDA, List.of(
                new Tarifa(TipoTarifa.IOF, new BigDecimal("0.01")),
                new Tarifa(TipoTarifa.PIS, new BigDecimal("0.022"))
        ));
        tarifas.put(Categoria.AUTO, List.of(
                new Tarifa(TipoTarifa.IOF, new BigDecimal("0.055")),
                new Tarifa(TipoTarifa.PIS, new BigDecimal("0.04")),
                new Tarifa(TipoTarifa.COFINS, new BigDecimal("0.01"))
        ));
        tarifas.put(Categoria.VIAGEM, List.of(
                new Tarifa(TipoTarifa.IOF, new BigDecimal("0.02")),
                new Tarifa(TipoTarifa.PIS, new BigDecimal("0.04")),
                new Tarifa(TipoTarifa.COFINS, new BigDecimal("0.01"))
        ));
        tarifas.put(Categoria.RESIDENCIAL, List.of(
                new Tarifa(TipoTarifa.IOF, new BigDecimal("0.04")),
                new Tarifa(TipoTarifa.COFINS, new BigDecimal("0.03"))
        ));
        tarifas.put(Categoria.PATRIMONIAL, List.of(
                new Tarifa(TipoTarifa.IOF, new BigDecimal("0.05")),
                new Tarifa(TipoTarifa.PIS, new BigDecimal("0.03"))
        ));
    }
}
