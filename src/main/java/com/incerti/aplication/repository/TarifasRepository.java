package com.incerti.aplication.repository;

import com.incerti.domain.model.Produto;
import com.incerti.domain.model.Tarifa;
import com.incerti.domain.model.type.Categoria;

import java.util.List;

public interface TarifasRepository {
    /**
     * Retorna uma lista de {@link Tarifa} relacionada a {@link Categoria}
     *
     * @param categoria {@link Categoria} ao qual a lista de {@link Tarifa} pertence
     *
     * @return Lista de {@link Tarifa} relacionadas a {@link Categoria} consultada
     */
    List<Tarifa> getTarifas(Categoria categoria);
}
