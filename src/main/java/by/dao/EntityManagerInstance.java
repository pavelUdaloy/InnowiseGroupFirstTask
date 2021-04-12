package by.dao;

import lombok.Getter;

import javax.persistence.EntityManager;

@Getter
public class EntityManagerInstance {
    private EntityManager entityManager;
    private SessionFactory sessionFactory;

    public EntityManagerInstance(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        entityManager = sessionFactory.getSessionFactory().createEntityManager();
    }

    public void preDestroy() {
        entityManager.clear();
        entityManager.close();
    }
}
