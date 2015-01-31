package points.ejb.group.dao;

import points.ejb.dao.GenericDaoImpl;
import points.group.dao.UserStatusDao;
import points.group.dto.UserStatus;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 19.10.2014.
 */
@Stateless
public class UserStatusDaoImpl extends GenericDaoImpl<UserStatus> implements UserStatusDao {
}
