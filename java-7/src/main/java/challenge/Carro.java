package challenge;

public class Carro {

    private final Motorista motorista;
    private final String placa;
    private final Cor cor;

    // Constructor
    private Carro(CarroBuilder builder) {
        this.motorista = builder.getMotorista();
        this.placa = builder.getPlaca();
        this.cor = builder.getCor();
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

        public Carro build() throws NullPointerException, EstacionamentoException {
            if (getCor() == null || getPlaca().trim().equals(""))
                throw new NullPointerException();
            else if (getMotorista() == null)
                throw new EstacionamentoException("Carro deve ter motorista");

            return new Carro(this);
        }

        public CarroBuilder withMotorista(Motorista motorista) {
            this.motorista = motorista;
            return this;
        }

        public CarroBuilder withPlaca(String placa) {
            this.placa = placa;
            return this;
        }

        public CarroBuilder withCor(Cor cor) {
            this.cor = cor;
            return this;
        }

        // Getters
        private Motorista getMotorista() {
            return motorista;
        }

        private Cor getCor() {
            return cor;
        }

        private String getPlaca() {
            return placa;
        }
    }
}
