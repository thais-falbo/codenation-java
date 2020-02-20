package challenge;

public class Motorista {

    private final String nome;
    private final int idade;
    private final int pontos;
    private final String habilitacao;

    // Constructor
    private Motorista(MotoristaBuilder builder) {
        this.nome = builder.nome;
        this.idade = builder.idade;
        this.pontos = builder.pontos;
        this.habilitacao = builder.habilitacao;
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

        public Motorista build() {
            withNome(nome);
            withHabilitacao(habilitacao);
            withIdade(idade);
            withPontos(pontos);

            return new Motorista(this);
        }

        public MotoristaBuilder withNome(String nome) throws NullPointerException{
            if(validaNome(nome)) throw new NullPointerException();

            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) throws IllegalArgumentException {
            if (validaIdade(idade)) throw new IllegalArgumentException();

            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) throws IllegalArgumentException {
            if (validaPontos(pontos)) throw new IllegalArgumentException();

            this.pontos = pontos;
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) throws NullPointerException {
            if (validaHabilitacao(habilitacao)) throw new NullPointerException();

            this.habilitacao = habilitacao;
            return this;
        }

        // Validators
        private Boolean validaHabilitacao(String habilitacao) {
            return habilitacao.trim().isEmpty();
        }

        private Boolean validaNome (String nome) {
            return nome.trim().isEmpty();
        }

        private Boolean validaPontos (int pontos) {
            return pontos < 0;
        }

        private Boolean validaIdade (int idade) {
            return idade < 0;
        }
    }
}
