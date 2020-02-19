package br.com.codenation.calculadora;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculadoraSalarioTest {

	@Test
	public void salarioLiquidoIsNotNull() {
		assertNotNull(new CalculadoraSalario().calcularSalarioLiquido(1000.0));
	}
}