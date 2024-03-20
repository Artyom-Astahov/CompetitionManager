package by.artem.dao.classes;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class SportCategory {
    private int id;
    private Enum<SportCategoryEnum> category;

}
