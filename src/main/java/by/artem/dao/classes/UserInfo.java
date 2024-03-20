package by.artem.dao.classes;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserInfo {
    private int id;
    private int userId;
    private String name;
    private int weight;
    private int categoryId;
    private Date dateBirth;

}
