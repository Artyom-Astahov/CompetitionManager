package by.artem.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Slf4j
@Table(schema = "public")
public class User implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private UserInfo userInfo;
    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "participants", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_catalog_id"))
    private List<CompetitionCatalog> competitionCatalogs = new ArrayList<>();




    public void addCompetitionCatalog(CompetitionCatalog competitionCatalog) {
        log.info("Добавление соревнований в список соревнований в классе Users");
        log.debug("Проверка приходящего класса CompetitionCatalog в классе Users, для добавления в List {}",
                competitionCatalog);
        competitionCatalogs.add(competitionCatalog);
        competitionCatalog.getUsers().add(this);
    }

}
