package by.entity.xmlmapping;

import by.entity.dao.response.UserDaoResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class XXX {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        XXX userRunner = new XXX();

        System.out.println("Adding developer's records to the DB");
        /**
         *  Adding developer's records to the database (DB)
         */
        userRunner.addUser("P", "Udaloi", "JavaDeveloper@fff.com");
        userRunner.addUser("YYY", "XXX", "CDeveloper@fff.com");
        userRunner.addUser("D", "K", "UIDeveloper@fff.com");

        System.out.println("List of users");
        /**
         * List users
         */
        List<UserDaoResponse> users = userRunner.listUsers();
        for (UserDaoResponse userDaoResponse : users) {
            System.out.println(userDaoResponse);
        }
        System.out.println("===================================");
        System.out.println("Final list of developers");
    }

    public void addUser(String firstName, String lastName, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        UserDaoResponse userDaoResponse = new UserDaoResponse();
        userDaoResponse.setEmail(email);
        userDaoResponse.setFirstName(firstName);
        userDaoResponse.setLastName(lastName);
        session.save(userDaoResponse);
        transaction.commit();
        session.close();
    }

    public List listUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List users = session.createQuery("FROM UserDaoResponse").list();

        transaction.commit();
        session.close();
        return users;
    }

}