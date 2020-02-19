package challenge;

public class Motorista {

    private final String nome;
    private final int idade;
    private final int pontos;
    private final String habilitacao;

    // Constructor
    private Motorista(MotoristaBuilder builder) {
        this.nome = builder.getNome();
        this.idade = builder.getIdade();
        this.pontos = builder.getPontos();
        this.habilitacao = builder.getHabilitacao();
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    // Builder
    public static class MotoristaBuilder {
        private String nome;
        private int idade;
        private int pontos;
        private String habilitacao;

        private MotoristaBuilder() {
        }

        public Motorista build() throws NullPointerException, IllegalArgumentException, EstacionamentoException {
            if (getHabilitacao().trim().equals("") || getNome().trim().equals(""))
                throw new NullPointerException();
            else if (getIdade() < 0 || getPontos() < 0)
                throw new IllegalArgumentException();
            else if (getIdade() > 0 && getIdade() < 18)
                throw new EstacionamentoException("Não pode estacionar motorista de menor");
            else if (getPontos() > 20)
                throw new EstacionamentoException("Não pode estacionar motorista com mais de 20 pontos");

            return new Motorista(this);
        }

        public MotoristaBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) throws IllegalArgumentException {
            if (idade < 0) throw new IllegalArgumentException();

            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) throws IllegalArgumentException {
            if (pontos < 0) throw new IllegalArgumentException();

            this.pontos = pontos;
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            return this;
        }

        // Getters
        private String getNome() {
            return nome;
        }

        private int getIdade() {
            return idade;
        }

        private int getPontos() {
            return pontos;
        }

        private String getHabilitacao() {
            return habilitacao;
        }
    }
}
