package by.dao;

import by.exception.ConnectionWithDBLostException;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;

@NoArgsConstructor
public class EntityManagerProvider {

    private static final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<>();
    private static final SessionFactory sessionFactory = new SessionFactory();

    public static EntityManager getEntityManager() throws ConnectionWithDBLostException {
        try {
            if (THREAD_LOCAL.get() == null) {
                THREAD_LOCAL.set(sessionFactory.getEntityManager());
            }
            return THREAD_LOCAL.get();
        } catch (Exception e) {
            throw new ConnectionWithDBLostException();
        }
    }

    public static void clear() throws ConnectionWithDBLostException {
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager != null) {
                entityManager.clear();
                entityManager.close();
                THREAD_LOCAL.remove();
            }
        } catch (IllegalStateException e) {
            throw new ConnectionWithDBLostException();
        }
    }
}