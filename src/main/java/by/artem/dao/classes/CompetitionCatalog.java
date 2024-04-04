package by.artem.dao.classes;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "users")
@Entity
public final class CompetitionCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateEvent;
    private String description;
    @Builder.Default
    @ManyToMany(mappedBy = "competitionCatalogs", cascade = CascadeType.ALL)
    private List<Users> users = new ArrayList<>();



}
