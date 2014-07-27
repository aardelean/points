package points.ejb.user.dao;

import points.dto.user.User;
import points.user.dao.UserDaoLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

/**
 * Created by aardelean on 19.07.2014.
 */
@Stateless
public class UserDao implements UserDaoLocal{

    @PersistenceContext(name="users-pu")
    private EntityManager entityManager;

    @Transactional
    public void saveUser(User user){
        entityManager.persist(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return (User)entityManager.createQuery("FROM User WHERE username:=username").setParameter("username",username).getSingleResult();
    }

    @Override
    public User findUserById(String id) {
        return entityManager.find(User.class,id);
    }
}
