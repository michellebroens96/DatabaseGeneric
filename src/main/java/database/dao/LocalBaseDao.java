package database.dao;
import database.model.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class LocalBaseDao <TEntity extends BaseEntity> implements IBaseDao<TEntity> {

    protected Map<Long, TEntity> entities;
    protected AtomicLong idCounter;

    protected LocalBaseDao() {
        this.entities = new HashMap<>();
        idCounter = new AtomicLong(1L);
    }

    public Collection<TEntity> getAll() {
        return new ArrayList<>(entities.values());
    }

    public TEntity get(@NotNull Long id) {
        return entities.get(id);
    }

    public TEntity add(@NotNull TEntity entity) {
        entity.setId(idCounter.getAndIncrement());
        entities.put(entity.getId(), entity);
        return entity;
    }

    public TEntity update(@NotNull TEntity entity) {
        if (!entities.containsKey(entity.getId())) {
            return null;
        }
        entities.replace(entity.getId(), entity);
        return entity;
    }

    public void delete(@NotNull TEntity entity) {
        delete(entity.getId());
    }

    public void delete(@NotNull Long id) {
        entities.remove(id);
    }
}
