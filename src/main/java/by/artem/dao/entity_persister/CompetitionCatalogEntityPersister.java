package by.artem.dao.entity_persister;

import by.artem.dao.classes.CompetitionCatalog;
import by.artem.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class CompetitionCatalogEntityPersister extends EntityPersister<CompetitionCatalog> {
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
}
