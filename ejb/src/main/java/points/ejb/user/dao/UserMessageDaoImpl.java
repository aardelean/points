package points.ejb.user.dao;

import points.ejb.dao.GenericDaoImpl;
import points.message.dto.UserMessage;
import points.user.dao.UserMessageDao;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 22.11.2014.
 */
@Stateless
public class UserMessageDaoImpl extends GenericDaoImpl<UserMessage>  implements UserMessageDao{
}
