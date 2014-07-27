package points.user.dao;

import points.dto.user.User;

import javax.ejb.Local;

/**
 * Created by aardelean on 19.07.2014.
 */
@Local
public interface UserDaoLocal {
    public void saveUser(User user);
    public User findUserByUserName(String username);
    public User findUserById(String id);
}
