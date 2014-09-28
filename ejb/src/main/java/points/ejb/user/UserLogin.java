package points.ejb.user;

import points.dao.user.UserLoginLocal;
import points.dao.user.dao.UserDaoLocal;
import points.dto.user.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by alex on 7/12/2014.
 */
@Stateless
public class UserLogin implements UserLoginLocal{

    @EJB
    private UserDaoLocal dao;
    @Override
    public Long login(String username, String password){
        User contact = new User();
        contact.setUsername(username);
        contact.setPassword(password);
        dao.saveUser(contact);
        return contact.getId();
    }
}
