package by.artem.dao.entity_persister;

import by.artem.dao.classes.QUser;
import by.artem.dao.classes.RolesEnum;
import by.artem.dao.classes.User;
import by.artem.dao.utils.HibernateUtil;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

import static by.artem.dao.classes.QUser.user;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntityPersister extends EntityPersister<User> {

    @Getter
    private static final UserEntityPersister INSTANCE = new UserEntityPersister();

    @Override
    public User getById(Serializable id) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User obj = session.get(User.class, id);
            session.getTransaction().commit();
            return obj;
        }
    }

    @Override
    public List<User> getAll() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> list = session.createCriteria(User.class).list();
            session.getTransaction().commit();
            return list;
        }
    }


    /**
     * Получить роль у юзера
     */
    public RolesEnum getRoleFromUser(User user) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            RolesEnum results = new JPAQuery<User>(session)
                    .select(QUser.user.role)
                    .from(QUser.user)
                    .where(QUser.user.id.eq(user.getId()))
                    .fetch().get(0);
            session.getTransaction().commit();
            return results;
        }


    }
}
