package com.incerti.aplication.repository;

import com.incerti.domain.model.Tarifa;
import com.incerti.domain.model.type.Categoria;

import java.util.List;

// TODO: Doc
public interface TarifasRepository {
    List<Tarifa> getTarifas(Categoria categoria);
}
