package points.message;

import javax.ejb.Local;

/**
 * Created by aardelean on 18.10.2014.
 */

@Local
public interface MessageService {

    void createAndSendGroupMessage(Long senderId,Long groupId, String content, String location);

    void createAndSendUserMessage(Long senderId,Long receiverId, String content, String location);
}
