package points.message;

import points.transport.message.MessageTransport;

/**
 * Created by aardelean on 13.12.2014.
 */
public interface MessageFacade {

    MessageTransport createAndSendGroupMessage(MessageTransport message);

    MessageTransport createAndSendUserMessage(MessageTransport message);
}
