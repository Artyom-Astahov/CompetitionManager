package by.artem.dao;

import by.artem.dao.utils.HibernateUtil;
import by.artem.dao.utils.TestDataImporter;
import lombok.Cleanup;
import org.hibernate.SessionFactory;

public class HibernateRunner {
    public static void main(String[] args) {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        // Проверь чтобы было раскомичено create в конфиге hibernate!
        TestDataImporter.importData(sessionFactory);
    }
}
