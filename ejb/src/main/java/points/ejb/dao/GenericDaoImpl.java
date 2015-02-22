package points.ejb.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import points.GenericDao;

/**
 * Created by aardelean on 04.10.2014.
 */
public abstract class GenericDaoImpl<T extends Object> implements GenericDao<T> {

    @PersistenceContext(unitName = "persist")
    protected EntityManager em;

    @Override
    public T save(T t) {
        em.persist(t);
        return t;
    }

    @Override
    public T findById(Serializable id) {
        T result = null;
        try {
            Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            result = em.find(persistentClass, id);
        }
        catch (NoResultException ex) {
            // null should be return in case the entity doesn't exist, and no exception should be here raised.
        }
        return result;

    }

    @Override
    public T update(T t) {
        return em.merge(t);
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }
}
