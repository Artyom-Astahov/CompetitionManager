package by.artem.dao.entity_persister;

import by.artem.dao.classes.Roles;
import by.artem.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class RolesEntityPersister extends EntityPersister<Roles> {
    @Override
    public Roles getById(Serializable id) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Roles obj = session.get(Roles.class, id);
            session.getTransaction().commit();
            return obj;
        }
    }

    @Override
    public List<Roles> getAll() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Roles> list = session.createCriteria(Roles.class).list();
            session.getTransaction().commit();
            return list;
        }
    }
}
