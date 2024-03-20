package by.artem.dao.classes;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Users {
    private int id;
    private String login;
    private String password;
    private int roleId;

}
