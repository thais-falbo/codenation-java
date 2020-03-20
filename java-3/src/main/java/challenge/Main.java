package challenge;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private List<Player> players = readCSV();

    private static List<Player> readCSV() {
        FileReader fileReader = null;
        File file;
        List<Player> players = new ArrayList<>();

        try {
            file = new File(Main.class.getClassLoader().getResource("data.csv").getFile());
            fileReader = new FileReader(file);
            CsvToBean csvToBean = new CsvToBeanBuilder<>(fileReader).withType(Player.class).withSeparator(',').build();
            players = csvToBean.parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return players;
    }

    // Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
    public int q1() {
        return players.stream()
                .collect(Collectors.groupingBy(Player::getNationality, Collectors.counting()))
                .size();
    }

    // Quantos clubes (coluna `club`) diferentes existem no arquivo?
    // Obs: Existem jogadores sem clube.
    public int q2() {
        return players.stream()
                .filter(player -> !player.getClub().isEmpty())
                .collect(Collectors.groupingBy(Player::getClub, Collectors.counting()))
                .size();
    }

    // Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.
    public List<String> q3() {
        return players.stream()
                .limit(20)
                .map(Player::getFullName)
                .collect(Collectors.toList());
    }

    // Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
    // (utilize as colunas `full_name` e `eur_release_clause`)
    public List<String> q4() {
        return players.stream()
                .filter(player -> player.getEurReleaseClause() != null)
                .sorted(Comparator.comparing(Player::getEurReleaseClause).reversed())
                .map(Player::getFullName)
                .limit(10)
                .collect(Collectors.toList());
    }

    // Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
    // (utilize as colunas `full_name` e `birth_date`)
    public List<String> q5() {
        return players.stream()
                .sorted(Comparator.comparing(Player::getBirthDate).thenComparing(Player::getEurWage))
                .map(Player::getFullName)
                .limit(10)
                .collect(Collectors.toList());
    }

    // Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
    // (utilize a coluna `age`)
    public Map<Integer, Integer> q6() {
        return players.stream()
                .collect(
                        Collectors.groupingBy(Player::getAge, Collectors.reducing(0, e -> 1, Integer::sum))
                );
    }
}
