package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estacionamento {
    private final int MAXIMO_VAGAS = 10;
    private final int IDADE_MINIMA_SENIOR = 55;
    private List<Carro> carrosEstacionados = new ArrayList<>();

    public void estacionar(Carro carro) throws EstacionamentoException {
        List<Motorista> motoristasSeniors = getMotoristasSeniors();

        // Caso esteja lotado
        if (carrosEstacionados.size() == MAXIMO_VAGAS) {
            if (motoristasSeniors.size() == MAXIMO_VAGAS) {
                // Caso todos os motoristas são seniors
                throw new EstacionamentoException("Não há mais vagas");
            } else if (carrosEstacionados.get(0).getMotorista().getIdade() > IDADE_MINIMA_SENIOR) {
                // Se o primeiro carro é senior, procura o próximo com idade abaixo
                Carro carroPraRemover = carrosEstacionados
                        .stream()
                        .skip(1)
                        .filter(carroEstacionado -> carroEstacionado.getMotorista().getIdade() < IDADE_MINIMA_SENIOR)
                        .findFirst()
                        .orElse(null);

                carrosEstacionados.remove(carroPraRemover);
            } else {
                // Remove o primeiro carro
                carrosEstacionados.remove(0);
            }
        }

        carrosEstacionados.add(carro);
    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosEstacionados.indexOf(carro) != -1;
    }

    private List<Motorista> getMotoristasSeniors() {
        return carrosEstacionados
                .stream()
                .filter(carroEstacionado -> carroEstacionado.getMotorista().getIdade() > IDADE_MINIMA_SENIOR)
                .map(Carro::getMotorista)
                .collect(Collectors.toList());
    }
}
