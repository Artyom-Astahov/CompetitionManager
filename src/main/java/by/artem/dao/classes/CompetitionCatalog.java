package by.artem.dao.classes;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class CompetitionCatalog {
    private int id;
    private Date dateEvent;
    private String description;
}
