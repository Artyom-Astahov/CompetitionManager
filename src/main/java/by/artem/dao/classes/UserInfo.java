package by.artem.dao.classes;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Slf4j
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users userId;
    private String name;
    private int weight;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private SportCategory categoryId;
    private Date dateBirth;

    public void setUser(Users user){
        log.info("Установка User в UserInfo");
        log.debug("Установка User в UserInfo, проверка приходящего класса User {}", user);
        this.userId = user;
        user.setUserInfo(this);
    }

}
