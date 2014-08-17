package points.user.dao;

import points.dto.user.Contact;

import javax.ejb.Local;
import javax.transaction.Transactional;

/**
 * Created by aardelean on 19.07.2014.
 */
@Local
public interface UserDaoLocal {
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveUser(Contact contact);
    public Contact findUserByUserName(String username);
    public Contact findUserById(Integer id);
}
