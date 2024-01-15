package com.incerti.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class PrecoUtils {

    public static BigDecimal umPreco() {
        return BigDecimal.valueOf(Math.random());
    }
}
