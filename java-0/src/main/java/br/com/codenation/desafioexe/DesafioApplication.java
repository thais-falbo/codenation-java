package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		int primeiroTermo = 0;
		int segundoTermo = 1;

		List<Integer> serieFib = new ArrayList<>(Arrays.asList(primeiroTermo, segundoTermo));

		int somaTermos = 0;

		do {
			somaTermos = primeiroTermo + segundoTermo;
			serieFib.add(somaTermos);
			primeiroTermo = segundoTermo;
			segundoTermo = somaTermos;
		} while (somaTermos < 350);

		return serieFib;
	}

	public static Boolean isFibonacci(Integer a) {
		List<Integer> serieFib = fibonacci();
		return serieFib.contains(a);
	}

}