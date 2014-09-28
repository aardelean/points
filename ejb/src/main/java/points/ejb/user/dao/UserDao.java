package points.ejb.user.dao;

import points.dao.user.dao.UserDaoLocal;
import points.dto.user.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by aardelean on 19.07.2014.
 */
@Stateless
public class UserDao implements UserDaoLocal{

    @PersistenceContext(name="mysql-pu")
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveUser(User contact){
        entityManager.persist(contact);
    }

    @Override
    public User findUserByUserName(String username) {
        return (User)entityManager.createQuery("FROM User WHERE username:=username").setParameter("username",username).getSingleResult();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class,id);
    }
}
