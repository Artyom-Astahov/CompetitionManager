package by.artem.dao.entity_persister;

import by.artem.dao.classes.CompetitionCatalog;
import by.artem.dao.classes.User;
import by.artem.dao.utils.HibernateUtil;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

import static by.artem.dao.classes.QCompetitionCatalog.competitionCatalog;
import static by.artem.dao.classes.QUser.user;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompetitionCatalogEntityPersister extends EntityPersister<CompetitionCatalog> {

    @Getter
    private static final CompetitionCatalogEntityPersister INSTANCE = new CompetitionCatalogEntityPersister();

    @Override
    public CompetitionCatalog getById(Serializable id) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CompetitionCatalog obj = session.get(CompetitionCatalog.class, id);
            session.getTransaction().commit();
            return obj;
        }
    }

    @Override
    public List<CompetitionCatalog> getAll() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<CompetitionCatalog> list = session.createCriteria(CompetitionCatalog.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    /**
     * Получить список участников соревнований {competition}
     */
    public List<User> getListUsersInCompetition(CompetitionCatalog competition) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> usersInCompetition = new JPAQuery<User>(session)
                    .select(user)
                    .from(competitionCatalog)
                    .join(competitionCatalog.users, user)
                    .where(competitionCatalog.eq(competition))
                    .fetch();
            session.getTransaction().commit();
            return usersInCompetition;
        }
    }

}
