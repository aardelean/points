package points.dao.user.dao;

import points.dto.user.User;

import javax.ejb.Local;
import javax.transaction.Transactional;

/**
 * Created by aardelean on 19.07.2014.
 */
@Local
public interface UserDaoLocal {
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveUser(User contact);
    public User findUserByUserName(String username);
    public User findUserById(Long id);
}
