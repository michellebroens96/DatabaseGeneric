package database.dao;

import database.model.BaseEntity;
import database.utility.ISimpleSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.Collection;

public abstract class BaseDao<TEntity extends BaseEntity> implements IBaseDao<TEntity> {

    /**
     * The injected session factory used to generate sessions and access data in Hibernate.
     */
    @Inject
    protected ISimpleSessionFactory sessionFactory;

    /**
     * The class object used for things like get() and getAll().
     * This is assigned in the constructor of the Dao object.
     * This has to be done because Hibernate does not work with type parameters
     * but rather work with the Class object.
     * This is the best way we can make this generic.
     * If the methods like get() and getAll() break because of this,
     * they can be overwritten as all methods are Virtual in Java by default.
     */
    protected Class<TEntity> persistentClass;


    protected BaseDao() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<TEntity> getAll() {
        Session session = getSession();
        Query query = session.createQuery("FROM " + persistentClass.getName(), persistentClass);
        return query.getResultList();
    }

    @Override
    public TEntity get(Long id) {
        return getSession().get(persistentClass, id);
    }

    @Override
    public TEntity add(TEntity entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.persist(entity);
        transaction.commit();
        return entity;
    }

    @Override
    public TEntity update(TEntity entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.update(entity);
        transaction.commit();
        return entity;
    }

    @Override
    public void delete(TEntity entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.delete(entity);
        transaction.commit();
    }

    @Override
    public void delete(Long id) {
        TEntity entity = get(id);
        delete(entity);
    }

    /**
     * Gets the current session from the session factory.
     * This is RequestScoped so this WILL count for multiple objects.
     *
     * @return the current session.
     */
    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
