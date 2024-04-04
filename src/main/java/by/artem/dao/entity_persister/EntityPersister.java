package by.artem.dao.entity_persister;

import by.artem.dao.classes.CompetitionCatalog;
import by.artem.dao.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class EntityPersister<T> {


    abstract public T getById(Serializable id);

    abstract public List<T> getAll();




    public void save(T obj) {
        log.info("Сохранение в БД {}", this);
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        }
    }

    public void update(T obj) {
        log.info("Обновление в БД {}", this);
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        }
    }

    public void delete(T obj) {
        log.info("Удаление из БД {}", this);
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        }
    }
}
