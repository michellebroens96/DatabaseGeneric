package database.service;

import database.dao.IBaseDao;
import database.model.BaseEntity;
import lombok.NonNull;

import javax.inject.Inject;
import java.util.Collection;

public abstract class BaseService <TEntity extends BaseEntity, TDao extends IBaseDao<TEntity>> implements IBaseService<TEntity> {

    @Inject
    protected TDao dao;

    @Override
    public TEntity get(Long id) {
        checkGivenId(id);
        return dao.get(id);
    }

    @Override
    public Collection<TEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public TEntity add(@NonNull TEntity entity) throws NullPointerException {
        checkObjectForNull(entity);
        return dao.add(entity);
    }

    @Override
    public TEntity update(@NonNull TEntity entity) throws NullPointerException {
        checkGivenEntity(entity);
        return dao.update(entity);
    }

    @Override
    public void delete(@NonNull TEntity entity) {
        checkGivenEntity(entity);
        dao.delete(entity);
    }

    @Override
    public void delete(Long id) {
        checkGivenId(id);
        dao.delete(id);
    }

    protected static void checkObjectForNull(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The given entity must not be null");
        }
    }

    protected void checkGivenEntity(TEntity entity) {
        if (entity == null || entity.getId() < 1) {
            throw new IllegalArgumentException("The given entity must not be null and have an id greater than 1");
        }
    }

    protected static void checkGivenId(Long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
    }
}
