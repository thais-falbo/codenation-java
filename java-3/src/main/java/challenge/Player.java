package challenge;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @CsvBindByName(column = "full_name", required = true)
    private String fullName;

    @CsvBindByName(column = "age")
    private Integer age;

    @CsvBindByName(column = "club")
    private String club;

    @CsvBindByName(column = "nationality")
    private String nationality;

    @CsvBindByName(column = "eur_release_clause")
    private Double eurReleaseClause;

    @CsvBindByName(column = "eur_wage")
    private Double eurWage;

    @CsvDate("yyyy-MM-dd")
    @CsvBindByName(column = "birth_date")
    private Date birthDate;
}
