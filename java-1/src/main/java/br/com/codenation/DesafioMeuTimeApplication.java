package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    private Map<Long, Time> timesCadastrados;
    private Map<Long, Jogador> jogadoresCadastrados;

    public DesafioMeuTimeApplication() {
        this.timesCadastrados = new HashMap<>();
        this.jogadoresCadastrados = new HashMap<>();
    }

    private Time buscarTime(Long idTime) throws TimeNaoEncontradoException {
        Time time = timesCadastrados.get(idTime);

        if (time == null) throw new TimeNaoEncontradoException();

        return time;
    }

    private Jogador buscarJogador(Long idJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadoresCadastrados.get(idJogador);

        if (jogador == null) throw new JogadorNaoEncontradoException();

        return jogador;
    }

    private List<Jogador> getListaJogadoresTime(Long idTime) {
        return jogadoresCadastrados
                .values()
                .stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .collect(Collectors.toList());
    }

    /**
     * Realiza a inclusão de um novo time
     *
     * @param id                    Identificador do time
     * @param nome                  Nome do time
     * @param dataCriacao           Data de criação do time
     * @param corUniformePrincipal  Cor do uniforme principal do time
     * @param corUniformeSecundario Cor do uniforme secundário do time
     * @throws IdentificadorUtilizadoException Caso identificador já exista
     */
    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException {
        if (timesCadastrados.get(id) != null) throw new IdentificadorUtilizadoException();

        Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

        timesCadastrados.put(id, novoTime);
    }

    /**
     * Realiza a inclusão de um novo jogador
     *
     * @param id              Identificador do jogador
     * @param idTime          Identificador do time
     * @param nome            Nome do jogador
     * @param dataNascimento  Data de nascimento do jogador
     * @param nivelHabilidade Nível de habilidade do jogador (0 a 100)
     * @param salario         Salário do jogador
     * @throws IdentificadorUtilizadoException Caso identificador(id) já exista
     * @throws TimeNaoEncontradoException      Caso time não exista
     */
    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws IdentificadorUtilizadoException, TimeNaoEncontradoException {
        if (timesCadastrados.get(idTime) == null) throw new TimeNaoEncontradoException();
        if (jogadoresCadastrados.get(id) != null) throw new IdentificadorUtilizadoException();

        Jogador novoJogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

        jogadoresCadastrados.put(id, novoJogador);
    }

    /**
     * Define um jogador como capitão do seu time. Um time deve ter apenas um capitão,
     * por tanto o capitão anterior voltará a ser apenas jogador.
     *
     * @param idJogador Identificador do jogador
     * @throws JogadorNaoEncontradoException Caso jogador não exista
     */
    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = buscarJogador(idJogador);

        buscarTime(jogador.getIdTime()).setCapitao(jogador);
    }

    /**
     * Busca identificador do capitão do time
     *
     * @param idTime Identificador do time
     * @return id do capitão do time
     * @throws TimeNaoEncontradoException   Caso time não exista
     * @throws CapitaoNaoInformadoException Caso capitão não exista
     */
    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException {
        Time time = buscarTime(idTime);

        Jogador capitao = time.getCapitao();

        if (capitao == null) throw new CapitaoNaoInformadoException();

        return capitao.getId();
    }

    /**
     * Busca nome do jogador pelo seu identificador
     *
     * @param idJogador Identificador do jogador
     * @return Nome do jogador
     * @throws JogadorNaoEncontradoException Caso jogador não exista
     */
    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadoresCadastrados.get(idJogador);

        if (jogador == null) throw new JogadorNaoEncontradoException();

        return jogador.getNome();
    }

    /**
     * Busca nome do time pelo identificador
     *
     * @param idTime Identificador do time
     * @return Nome do time
     * @throws TimeNaoEncontradoException Caso time não exista
     */
    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException {
        return buscarTime(idTime).getNome();
    }

    /**
     * Busca jogadores de um time
     *
     * @param idTime Identificador do time
     * @return Lista com o identificador de todos os jogadores do time, ordenada pelo id.
     * @throws TimeNaoEncontradoException Caso time não exista
     */
    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException {
        buscarTime(idTime);

        List<Jogador> listaJogadores = getListaJogadoresTime(idTime);

        return listaJogadores.stream().map(Jogador::getId).sorted().collect(Collectors.toList());
    }

    /**
     * Retorna o identificador do melhor jogador do time.
     *
     * @param idTime Identificador do time
     * @return Id do jogador
     * @throws TimeNaoEncontradoException Caso time não exista
     */
    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException {
        buscarTime(idTime);

        return getListaJogadoresTime(idTime)
                .stream()
                .max(Comparator.comparingInt(Jogador::getNivelHabilidade))
                .get()
                .getId();

    }

    /**
     * Retorna o identificador do jogador mais velho do time. Usar o menor identificador como critério de desempate.
     *
     * @param idTime Identificador do time
     * @return
     */
    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException {
        buscarTime(idTime);

        return Objects.requireNonNull(getListaJogadoresTime(idTime)
                .stream()
                .sorted(Comparator.comparingLong(Jogador::getId))
                .min(Comparator.comparing(Jogador::getDataNascimento))
                .get()
                .getId();
    }

    /**
     * Retorna uma lista com o identificador de todos os times cadastrado, ordenada pelo identificador.
     * Retorna uma lista vazia caso não encontre times cadastrados.
     *
     * @return
     */
    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        if (timesCadastrados.size() == 0) return new ArrayList<>();

        return new ArrayList<>(timesCadastrados.values())
                .stream()
                .map(Time::getId)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna o identificador do jogador com maior salário do time.
     * Usa o menor identificador como critério de desempate.
     *
     * @param idTime Identificador do time
     * @return Identificador do jogador
     * @throws TimeNaoEncontradoException Caso time não exista
     */
    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) throws TimeNaoEncontradoException {
        buscarTime(idTime);

        return getListaJogadoresTime(idTime)
                .stream()
                .sorted(Comparator.comparingLong(Jogador::getId))
                .max(Comparator.comparing(Jogador::getSalario))
                .get()
                .getId();
    }

    /**
     * Retorna o salário do jogador.
     *
     * @param idJogador Identificador do jogador
     * @return Salário do jogador
     * @throws JogadorNaoEncontradoException Caso jogador não exista
     */
    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException {
        return buscarJogador(idJogador).getSalario();
    }

    /**
     * Retorna uma lista com o identificador dos top melhores jogadores,
     * utiliza o menor identificador como critério de desempate.
     *
     * @param top Quantidade de jogares na lista
     * @return Caso não exista nenhum jogador cadastrado, retornar uma lista vazia.
     */
    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        if (jogadoresCadastrados.size() == 0) return new ArrayList<>();

        return new ArrayList<>(jogadoresCadastrados.values())
                .stream()
                .sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
                .limit(top)
                .map(Jogador::getId)
                .collect(Collectors.toList());
    }

    /**
     * Retorna a cor da camisa do time adversário.
     *
     * @param idTimeDaCasa Identificador do time de casa
     * @param idTimeDeFora Identificador do time de fora
     * @return Cor do uniforme do time adversário
     */
    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long idTimeDaCasa, Long idTimeDeFora) {
        Time timeCasa = buscarTime(idTimeDaCasa);
        Time timeFora = buscarTime(idTimeDeFora);

        // Caso a cor principal do time da casa seja igual a cor principal do time de fora, retornar cor secundária do time de fora.
        if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal()))
            return timeFora.getCorUniformeSecundario();

        // Caso a cor principal do time da casa seja diferente da cor principal do time de fora, retornar cor principal do time de fora.
        return timeFora.getCorUniformePrincipal();
    }
}
