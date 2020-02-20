package challenge;

public class Carro {

    private final Motorista motorista;
    private final String placa;
    private final Cor cor;

    // Constructor
    private Carro(CarroBuilder builder) {
        this.motorista = builder.motorista;
        this.placa = builder.placa;
        this.cor = builder.cor;
    }

    public static CarroBuilder builder() {
        return new CarroBuilder();
    }

    // Getters
    public Motorista getMotorista() {
        return motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    // Builder
    public static class CarroBuilder {

        private Motorista motorista;
        private String placa;
        private Cor cor;

        private CarroBuilder() {
        }

        public Carro build() {
            withCor(cor);
            withMotorista(motorista);
            withPlaca(placa);

            return new Carro(this);
        }

        public CarroBuilder withMotorista(Motorista motorista) {
            // Não validamos motorista pq o carro pode ser autônomo
            this.motorista = motorista;
            return this;
        }

        public CarroBuilder withPlaca(String placa) throws NullPointerException {
            if (validarPlaca(placa)) throw new NullPointerException("Carro deve ter placa");

            this.placa = placa;
            return this;
        }

        public CarroBuilder withCor(Cor cor) throws NullPointerException {
            if (validarCor(cor)) throw new NullPointerException("Carro deve ter cor");

            this.cor = cor;
            return this;
        }

        // Validação
        private Boolean validarPlaca (String placa) {
            return placa.trim().isEmpty();
        }

        private Boolean validarCor (Cor cor) {
            return cor == null;
        }
    }
}
