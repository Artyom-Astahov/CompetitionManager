package by.artem.dao;

import by.artem.entity.CompetitionCatalog;
import by.artem.utils.HibernateUtil;
import by.artem.dao.utils.TestDataImporter;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

public class HibernateRunner {
    public static void main(String[] args) {
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();) {

            // Проверь чтобы было раскомичено create в конфиге hibernate!
        TestDataImporter.importData(sessionFactory);
//            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
//                    new Class[]{Session.class},
//                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
//            session.beginTransaction();
//            CompetitionCatalogRepository competitionCatalogRepository = new CompetitionCatalogRepository(session);
//            CompetitionCatalog competitionCatalog = session.get(CompetitionCatalog.class, 1);
//            System.out.println(competitionCatalogRepository.getListUsersInCompetition(competitionCatalog));

        }

    }
}
