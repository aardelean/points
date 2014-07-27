package points.ejb.user;

import points.dto.user.User;
import points.ejb.user.dao.UserDao;
import points.user.UserLoginLocal;
import points.user.dao.UserDaoLocal;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by alex on 7/12/2014.
 */
@Stateless
public class UserLogin implements UserLoginLocal{

    @EJB
    private UserDaoLocal dao;

    public String login(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        dao.saveUser(user);
        return user.getId();
    }
}
