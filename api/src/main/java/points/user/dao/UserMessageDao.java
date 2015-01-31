package points.user.dao;

import points.GenericDao;
import points.message.dto.UserMessage;

import javax.ejb.Local;

/**
 * Created by aardelean on 22.11.2014.
 */
@Local
public interface UserMessageDao extends GenericDao<UserMessage> {
}
