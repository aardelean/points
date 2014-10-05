package points.ejb.user.dao;

import points.dao.user.dao.UserDao;
import points.dao.user.dto.User;
import points.ejb.dao.GenericDaoImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by aardelean on 19.07.2014.
 */
@Stateless
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @PersistenceContext(name="mysql-pu")
    private EntityManager entityManager;

    @Override
    public User findUserByUserName(String username) {
        return (User)entityManager.createQuery("FROM User WHERE username:=username").setParameter("username",username).getSingleResult();
    }

}
