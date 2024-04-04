package by.artem.dao.classes;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public final class SportCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private SportCategoryEnum category;
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserInfo> usersInfo;

}
