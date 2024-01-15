package com.incerti.adapter.persistence;

import com.incerti.aplication.repository.TarifasRepository;
import com.incerti.domain.model.Tarifa;
import com.incerti.domain.model.type.Categoria;
import com.incerti.domain.model.type.TipoTarifa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryTarifasRepository implements TarifasRepository {

    // TODO: Documentar

    private final Map<Categoria, List<Tarifa>> tarifas = new HashMap<>();

    public InMemoryTarifasRepository() {
        carregaTarifas();
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

    @Override
    public List<Tarifa> getTarifas(Categoria categoria) {
        return tarifas.get(categoria);
    }
}
