package by.artem.dao.entity_persister;

import by.artem.dao.CompetitionCatalogRepository;
import by.artem.entity.CompetitionCatalog;
import by.artem.entity.User;
import by.artem.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionCatalogEntityPersisterTest {

    @Test
    void getListUsersInCompetition() {
        CompetitionCatalog competitionCatalog;
        CompetitionCatalogRepository persister;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
                Session session = sessionFactory.openSession();) {
            persister = new CompetitionCatalogRepository(session);
            session.beginTransaction();
            competitionCatalog = session.get(CompetitionCatalog.class, 1);
            session.getTransaction().commit();
            List<User> users =  persister.getListUsersInCompetition(competitionCatalog);
            assertThat(users.size()).isEqualTo(6);
        }

    }
}
