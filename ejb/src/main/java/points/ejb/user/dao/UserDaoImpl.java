package points.ejb.user.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import points.ejb.dao.GenericDaoImpl;
import points.user.dao.UserDao;
import points.user.dto.User;

/**
 * Created by aardelean on 19.07.2014.
 */
@Stateless
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @Override
    public User findUserByUserName(String username) {
        return (User)em.createQuery("FROM User WHERE username=:username").setParameter("username",username).getSingleResult();
    }

    @Override
    public List<User> getUsersWithIds(List<Long> ids) {
        Query query = em.createQuery("FROM User WHERE id IN(:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }

}
