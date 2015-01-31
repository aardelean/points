package points.ejb.user.dao;

import points.ejb.dao.GenericDaoImpl;
import points.user.dao.UserDao;
import points.user.dto.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by aardelean on 19.07.2014.
 */
@Stateless
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @PersistenceContext(name="mysql-pu")
    private EntityManager entityManager;

    @Override
    public User findUserByUserName(String username) {
        return (User)entityManager.createQuery("FROM User WHERE username=:username").setParameter("username",username).getSingleResult();
    }

    @Override
    public List<User> getUsersWithIds(List<Long> ids) {
        Query query = entityManager.createQuery("FROM User WHERE id IN(:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }

}
