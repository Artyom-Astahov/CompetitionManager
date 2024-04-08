package by.artem.dao.entity_persister;

import by.artem.dao.classes.CompetitionCatalog;
import by.artem.dao.classes.User;
import by.artem.dao.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionCatalogEntityPersisterTest {
    private final CompetitionCatalogEntityPersister persister = CompetitionCatalogEntityPersister.getINSTANCE();
    @Test
    void getListUsersInCompetition() {
        CompetitionCatalog competitionCatalog = null;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
                Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            competitionCatalog = session.get(CompetitionCatalog.class, 1);
            session.getTransaction().commit();
        }

        List<User> users =  persister.getListUsersInCompetition(competitionCatalog);
        assertThat(users.size()).isEqualTo(6);
    }
}
