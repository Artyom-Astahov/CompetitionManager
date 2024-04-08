package by.artem.dao.entity_persister;

import by.artem.dao.classes.UserInfo;
import by.artem.dao.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoEntityPersister extends EntityPersister<UserInfo> {

    @Getter
    private static final UserInfoEntityPersister INSTANCE = new UserInfoEntityPersister();

    @Override
    public UserInfo getById(Serializable id) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            UserInfo obj = session.get(UserInfo.class, id);
            session.getTransaction().commit();
            return obj;
        }
    }

    @Override
    public List<UserInfo> getAll() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<UserInfo> list = session.createCriteria(UserInfo.class).list();
            session.getTransaction().commit();
            return list;
        }
    }
}
