package by.artem.dao;

import by.artem.entity.CompetitionCatalog;
import by.artem.entity.User;
import by.artem.utils.HibernateUtil;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static by.artem.dao.entity.QCompetitionCatalog.competitionCatalog;
import static by.artem.dao.entity.QUser.user;

public class CompetitionCatalogRepository extends BaseRepository<Integer, CompetitionCatalog> {
    public CompetitionCatalogRepository(EntityManager entityManager) {
        super(CompetitionCatalog.class, entityManager);
    }

    public List<User> getListUsersInCompetition(CompetitionCatalog competition) {

        return new JPAQuery<User>(this.getEntityManager())
                .select(user)
                .from(competitionCatalog)
                .join(competitionCatalog.users, user)
                .where(competitionCatalog.eq(competition))
                .fetch();

    }
}
