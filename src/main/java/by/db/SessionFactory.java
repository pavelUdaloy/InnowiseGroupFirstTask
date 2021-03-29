package by.db;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class SessionFactory {
    private final org.hibernate.SessionFactory sessionFactory;

    public SessionFactory() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public EntityManager getEntityManager() {
        return sessionFactory.createEntityManager();
    }
}
