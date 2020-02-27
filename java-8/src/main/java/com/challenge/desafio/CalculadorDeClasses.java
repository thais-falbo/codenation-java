package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object objeto) {
        return somarAtributosBigDecimal(objeto, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object objeto) {
        return somarAtributosBigDecimal(objeto, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object objeto) {
        return somar(objeto).subtract(subtrair(objeto));
    }

    private BigDecimal somarAtributosBigDecimal(Object objeto, Class annotation) {
        Field[] atributos = objeto.getClass().getDeclaredFields();
        BigDecimal resultado = BigDecimal.ZERO;

        for (Field atributo : atributos) {
            if (atributo.getAnnotation(annotation) != null && atributo.getType().equals(BigDecimal.class)) {
                try {
                    BigDecimal valor = buscarValorAtributo(objeto, atributo);
                    resultado = resultado.add(valor);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                resultado = resultado.add(BigDecimal.ZERO);
            }
        }

        return resultado;
    }

    private BigDecimal buscarValorAtributo(Object objeto, Field atributo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String nomeAtributo = atributo.getName();
        String nomeMetodo = "get" + Character.toUpperCase(nomeAtributo.charAt(0)) + nomeAtributo.substring(1);
        return (BigDecimal) objeto.getClass().getDeclaredMethod(nomeMetodo).invoke(objeto);
    }
}
