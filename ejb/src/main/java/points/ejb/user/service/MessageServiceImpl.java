package points.ejb.user.service;

import points.group.dao.GroupDao;
import points.group.dao.GroupMessageDao;
import points.group.dto.Group;
import points.message.MessageService;
import points.message.dto.GroupMessage;
import points.message.dto.Message;
import points.message.dto.UserMessage;
import points.user.dao.UserDao;
import points.user.dao.UserMessageDao;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

/**
 * Created by aardelean on 22.11.2014.
 */
@Stateless
public class MessageServiceImpl implements MessageService {

    @EJB
    private UserMessageDao userMessageDao;

    @EJB
    private GroupMessageDao groupMessageDao;

    @EJB
    private UserDao userDao;

    @EJB
    private GroupDao groupDao;

    @Override
    public Message createAndSendGroupMessage(Long senderId, Long groupId, String content, String location) {
        GroupMessage groupMessage = new GroupMessage();
        Group receiver = groupDao.findById(groupId);
        groupMessage.setGroup(receiver);
        populateMessage(groupMessage,senderId,content, location);
        groupMessageDao.save(groupMessage);
        return groupMessage;
    }

    @Override
    public UserMessage createAndSendUserMessage(Long senderId, Long receiverId, String content, String location) {
        UserMessage userMessage = new UserMessage();
        User receiver = userDao.findById(receiverId);
        userMessage.setUser(receiver);
        populateMessage(userMessage,senderId,content, location);
        userMessageDao.save(userMessage);
        return userMessage;
    }

    private void populateMessage(Message message, Long senderId, String content, String location){
        User sender = userDao.findById(senderId);
        message.setIssuer(sender);
        message.setContent(content);
        message.setLocation(location);
        message.setTime(new Date());
    }
}
