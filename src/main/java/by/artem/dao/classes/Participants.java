package by.artem.dao.classes;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class Participants {
    private int userId;
    private int competitionCatalogId;
}
