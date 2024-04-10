package by.artem.dao.entity_persister;

import by.artem.dao.UserRepository;
import by.artem.entity.RolesEnum;
import by.artem.entity.User;
import by.artem.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityPersisterTest {

    @Test
    public void getRoleFromUser() {
        User user;
        UserRepository persister;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();) {
            persister = new UserRepository(session);
            session.beginTransaction();

            user = session.get(User.class, 1);

            RolesEnum role = persister.getRoleFromUser(user);

            session.getTransaction().commit();
            assertThat(role).isEqualTo(RolesEnum.ATHLETE);
        }

    }
}
