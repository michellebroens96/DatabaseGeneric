package database.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * A simple implementation of the Hibernate Session Factory to make development easier.
 */
@Dependent
public class SimpleSessionFactory implements ISimpleSessionFactory {

    /**
     * The utility to create the hibernate session factory.
     */
    @Inject
    private HibernateUtility hibernateUtility;

    /**
     * The hibernate session factory.
     */
    private SessionFactory sessionFactory;

    /**
     * The session which is currently open for the request.
     */
    private Session currentSession;

    @Override
    public Session openSession() {
        if(sessionFactory == null) {
            configure();
        }

        return sessionFactory.openSession();
    }

    @Override
    public void configure() {
        if(sessionFactory == null) {
            sessionFactory = hibernateUtility.buildSessionFactory();
        }
    }

    @Override
    public Session getCurrentSession() {
        configure();
        if(currentSession == null) {
            currentSession = sessionFactory.openSession();
        }
        return currentSession;
    }

    @Override
    public StatelessSession openStatelessSession() {
        configure();
        return sessionFactory.openStatelessSession();
    }

    @PreDestroy
    private void closeSession() {
        if(currentSession != null && currentSession.isOpen()) {
            currentSession.close();
        }
    }
}
