package com.incerti.domain.model;

import com.incerti.domain.model.type.TipoTarifa;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Tarifa {
    private TipoTarifa tipoTarifa;
    private BigDecimal valor;
}
