package challenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scripts")
public class Quote {
    @Id
    private int id;

    @Column
    private String actor;

    @Column(name = "detail")
    private String quote;
}
