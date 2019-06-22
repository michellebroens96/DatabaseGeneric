package database.utility;

import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 * Configures the SessionFactory to connect to the proper data source.
 * This factory is a wrapper for the hibernate session factory.
 */
public interface ISimpleSessionFactory {

    /**
     * Configures the underlying factory using the provided path.
     */
    void configure();

    /**
     * Gets a session from the currently configured factory.
     *
     * @return the current sessions or a new session if one doesn't exist for this scope.
     */
    Session getCurrentSession();

    /**
     * Opens a new session from the currently configured factory.
     *
     * @return a newly opened session.
     */
    Session openSession();

    /**
     * Opens a stateless session instead of a regular session. Useful for one time operations.
     *
     * @return return a new opened stateless session.
     */
    StatelessSession openStatelessSession();

}
