package points.ejb.group.dao;

import points.ejb.dao.GenericDaoImpl;
import points.group.dao.GroupMessageDao;
import points.message.dto.GroupMessage;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 22.11.2014.
 */
@Stateless
public class GroupMessageDaoImpl extends GenericDaoImpl<GroupMessage> implements GroupMessageDao {
}
