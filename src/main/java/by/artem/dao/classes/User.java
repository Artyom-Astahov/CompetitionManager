package by.artem.dao.classes;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "userInfo")
@Entity
@Slf4j
@Table(schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "participants", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_catalog_id"))
    private List<CompetitionCatalog> competitionCatalogs = new ArrayList<>();



    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL)
    private UserInfo userInfo;


    public void addCompetitionCatalog(CompetitionCatalog competitionCatalog) {
        log.info("Добавление соревнований в список соревнований в классе Users");
        log.debug("Проверка приходящего класса CompetitionCatalog в классе Users, для добавления в List {}",
                competitionCatalog);
        competitionCatalogs.add(competitionCatalog);
        competitionCatalog.getUsers().add(this);
    }

}
