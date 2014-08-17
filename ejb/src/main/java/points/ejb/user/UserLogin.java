package points.ejb.user;

import points.dto.user.Contact;
import points.user.UserLoginLocal;
import points.user.dao.UserDaoLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by alex on 7/12/2014.
 */
@Stateless
public class UserLogin implements UserLoginLocal{

    @EJB
    private UserDaoLocal dao;

    public Integer login(String username, String password){
        Contact contact = new Contact();
        contact.setUsername(username);
        contact.setPassword(password);
        dao.saveUser(contact);
        return contact.getId();
    }
}
