package points.ejb.user.dao;

import points.dto.user.Contact;
import points.user.dao.UserDaoLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by aardelean on 19.07.2014.
 */
@Stateless
public class UserDao implements UserDaoLocal{

    @PersistenceContext(name="mysql-pu")
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveUser(Contact contact){
        contact.setCreationDate(new Date());
        contact.setLastUpdatedDate(new Date());
        entityManager.persist(contact);
    }

    @Override
    public Contact findUserByUserName(String username) {
        return (Contact)entityManager.createQuery("FROM User WHERE username:=username").setParameter("username",username).getSingleResult();
    }

    @Override
    public Contact findUserById(Integer id) {
        return entityManager.find(Contact.class,id);
    }
}
