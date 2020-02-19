package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    /**
     * Constructor da classe Jogador
     * @param id Identificador do jogador
     * @param idTime Identificador do time
     * @param nome Nome do jogador
     * @param dataNascimento Data de nascimento do jogador
     * @param nivelHabilidade Nível de habilidade do jogador (0 a 100)
     * @param salario Salário do jogador
     */
    public Jogador (Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
