package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		// Se salário é abaixo do salário mínimo;
		if (salarioBase < 1039.00)
			return 0;

		double salarioLiquido = calcularInss(salarioBase);

		if (salarioLiquido <= 3000.00)
			salarioLiquido = calcularValorDescontado(salarioLiquido, 0.0f);
		else if (salarioLiquido > 3000.00 && salarioLiquido <= 6000.00)
			salarioLiquido = calcularValorDescontado(salarioLiquido, 7.5f);
		else
			salarioLiquido = calcularValorDescontado(salarioLiquido, 15.0f);

		return Math.round(salarioLiquido);
	}
	
	private double calcularInss(double salarioBase) {
		float desconto;

		if (salarioBase <= 1500.00)
			desconto = 8.0f;
		else if (salarioBase > 1500.00 && salarioBase <= 4000.00)
			desconto = 9.0f;
		else
			desconto = 11.0f;

		return calcularValorDescontado(salarioBase, desconto);
	}

	// Calcula o valor com o desconto aplicado
	private double calcularValorDescontado(double valor, float desconto) {
		return valor - ((desconto * valor) / 100);
	}
}