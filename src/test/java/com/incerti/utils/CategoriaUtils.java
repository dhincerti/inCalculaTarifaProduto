package com.incerti.utils;

import com.incerti.domain.model.type.Categoria;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CategoriaUtils {

    public static Categoria umaCategoria() {
        return Categoria.values()[new Random().nextInt(Categoria.values().length)];
    }
}
