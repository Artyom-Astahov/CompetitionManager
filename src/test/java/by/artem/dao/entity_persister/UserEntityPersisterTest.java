package by.artem.dao.entity_persister;

import by.artem.dao.classes.RolesEnum;
import by.artem.dao.classes.User;
import by.artem.dao.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityPersisterTest {
    private final UserEntityPersister persister = UserEntityPersister.getINSTANCE();
    @Test
    public void getRoleFromUser() {
        User user = null;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
                Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            user = session.get(User.class, 1);
            session.getTransaction().commit();
        }

        RolesEnum role = persister.getRoleFromUser(user);
        assertThat(role).isEqualTo(RolesEnum.ATHLETE);
    }
}
