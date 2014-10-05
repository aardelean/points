package points.dao.user.dao;

import points.dao.GenericDao;
import points.dao.user.dto.User;

import javax.ejb.Local;

/**
 * Created by aardelean on 19.07.2014.
 */
@Local
public interface UserDao extends GenericDao<User>{

    public User findUserByUserName(String username);

}
