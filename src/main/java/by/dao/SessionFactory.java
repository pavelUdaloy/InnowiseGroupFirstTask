package by.dao;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;

public class SessionFactory {
    private EntityManager entityManager;
    org.hibernate.SessionFactory sessionFactory;

    public SessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void preDestroy() {
        entityManager.clear();
        entityManager.close();
        sessionFactory.close();
    }
}
