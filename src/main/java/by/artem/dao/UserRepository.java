package by.artem.dao;

import by.artem.dao.entity.QUser;
import by.artem.entity.RolesEnum;
import by.artem.entity.User;
import by.artem.utils.HibernateUtil;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Integer, User> {
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }


    public RolesEnum getRoleFromUser(User user) {

        return new JPAQuery<User>(getEntityManager())
                .select(QUser.user.role)
                .from(QUser.user)
                .where(QUser.user.id.eq(user.getId()))
                .fetch().get(0);
    }


}
