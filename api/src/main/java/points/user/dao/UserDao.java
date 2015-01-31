package points.user.dao;

import points.GenericDao;
import points.user.dto.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by aardelean on 19.07.2014.
 */
@Local
public interface UserDao extends GenericDao<User>{

    public User findUserByUserName(String username);

    public List<User> getUsersWithIds(List<Long> ids);

}
