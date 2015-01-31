package points.group.dao;

import points.GenericDao;
import points.message.dto.GroupMessage;

import javax.ejb.Local;

/**
 * Created by aardelean on 22.11.2014.
 */
@Local
public interface GroupMessageDao extends GenericDao<GroupMessage> {
}
