package points.ejb.user;

import points.user.dao.UserDao;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by alex on 7/12/2014.
 */
@Stateless
public class UserLogin implements points.user.UserLogin {

    @EJB
    private UserDao dao;
    @Override
    public Long login(String username, String password){
        User contact = new User();
        contact.setUsername(username);
        contact.setPassword(password);
        dao.save(contact);
        return contact.getId();
    }
}
