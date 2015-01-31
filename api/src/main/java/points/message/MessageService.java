package points.message;

import points.message.dto.Message;

import javax.ejb.Local;

/**
 * Created by aardelean on 18.10.2014.
 */

@Local
public interface MessageService {

    Message createAndSendGroupMessage(Long senderId,Long groupId, String content, String location);

    Message createAndSendUserMessage(Long senderId,Long receiverId, String content, String location);
}
