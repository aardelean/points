package points.group.dao;

import points.GenericDao;
import points.group.dto.UserStatus;

import javax.ejb.Local;

/**
 * Created by aardelean on 19.10.2014.
 */
@Local
public interface UserStatusDao extends GenericDao<UserStatus> {
}
