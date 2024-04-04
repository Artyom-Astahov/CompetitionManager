package by.artem.dao.entity_persister;

import by.artem.dao.classes.SportCategory;
import by.artem.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class SportCategoryEntityPersister extends EntityPersister<SportCategory> {
    @Override
    public SportCategory getById(Serializable id) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            SportCategory obj = session.get(SportCategory.class, id);
            session.getTransaction().commit();
            return obj;
        }
    }

    @Override
    public List<SportCategory> getAll() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<SportCategory> list = session.createCriteria(SportCategory.class).list();
            session.getTransaction().commit();
            return list;
        }
    }
}
