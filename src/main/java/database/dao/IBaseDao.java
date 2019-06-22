package database.dao;

import database.model.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * An interface to create a DAO by our standards.
 * @param <TEntity> the type of entity this DAO is meant for.
 */
public interface IBaseDao <TEntity extends BaseEntity> {
    /**
     * Gets all entities from the underlying data source.
     * @return a collection of entities.
     */
    Collection<TEntity> getAll();

    /**
     * Gets the entity based of its id from the underlying data source.
     * @param id the id of the entity intended to be found.
     * @return the found entity or null.
     */
    TEntity get(@NotNull Long id);

    /**
     * Adds the entity to the underlying data source.
     * @param entity the entity intended to be added to the data source.
     * @return the entity that was added or null the add operation failed.
     */
    TEntity add(@NotNull TEntity entity);

    /**
     * Updates the entity in the underlying data source.
     * @param entity the entity intended to be added to the data source.
     * @return the updated entity or null if this operation failed.
     */
    TEntity update(@NotNull TEntity entity);

    /**
     * Removes an entity out of the underlying data source.
     * @param entity the entity which is wanting to be removed
     */
    void delete(@NotNull TEntity entity);

    /**
     * Removes an entity out of the data source based on the given id.
     * @param id the id of the entity intended to be deleted from the data source.
     */
    void delete(@NotNull Long id);
}
