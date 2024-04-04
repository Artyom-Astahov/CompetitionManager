package by.artem.dao.entity_persister;

import by.artem.dao.classes.Users;
import by.artem.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class UsersEntityPersister extends EntityPersister<Users>{
    @Override
    public Users getById(Serializable id) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Users obj = session.get(Users.class, id);
            session.getTransaction().commit();
            return obj;
        }
    }

    @Override
    public List<Users> getAll() {
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Users> list =  session.createCriteria(Users.class).list();
            session.getTransaction().commit();
            return list;
        }
    }
}
