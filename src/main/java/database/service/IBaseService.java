package database.service;

import database.model.BaseEntity;

import java.util.Collection;

public interface IBaseService<TEntity extends BaseEntity> {


    /**
     * Users the underlying DAO to find an entity based on it's id.
     *
     * @param id
     *         the id of the entity intended to be found.
     *
     * @return the entity or null when the entity isn't found.
     *
     * @throws IllegalArgumentException
     *         when the id is lower than 1.
     */
    TEntity get(Long id);

    /**
     * Gets all the entities from the underlying DAO.
     *
     * @return a collection of entities.
     */
    Collection<TEntity> getAll();

    /**
     * Adds an entity using the underlying DAO.
     *
     * @param entity
     *         the entity intended to be added.
     *
     * @return the added entity or null when the operation has failed.
     *
     * @throws IllegalArgumentException
     *         when the given entity is null or invalid.
     */
    TEntity add(TEntity entity) throws NullPointerException;

    /**
     * Updates an entity using the underlying DAO.
     *
     * @param entity
     *         the entity intended to be updated.
     *
     * @return the updated entity or null when this operation has failed.
     *
     * @throws IllegalArgumentException
     *         when the entity is null or invalid.
     */
    TEntity update(TEntity entity) throws NullPointerException;

    /**
     * Deletes an entity using the underlying DAO.
     *
     * @param entity
     *         the entity intended to be deleted.
     *
     * @return a value indicating if the operation was completed successfully.
     *
     * @throws IllegalArgumentException
     *         when the entity is null or invalid.
     */
    void delete(TEntity entity);

    /**
     * Deletes an entity using the underlying DAO.
     *
     * @param id
     *         the id of the entity intended to be deleted.
     *
     * @return a value indicating if the operation was completed successfully.
     *
     * @throws IllegalArgumentException
     *         when the id is lower than 1 or the entity is not found.
     */
    void delete(Long id);
}
