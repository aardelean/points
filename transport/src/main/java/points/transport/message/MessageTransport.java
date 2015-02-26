package points.transport.message;

import points.transport.AbstractTransportEntity;
import points.transport.user.UserDetailsTransport;
import points.transport.group.GroupTransport;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 13.12.2014.
 */
public class MessageTransport extends AbstractTransportEntity implements Serializable {
    private UserDetailsTransport sender;
    private UserDetailsTransport receiver;
    private GroupTransport groupReceiver;
    private Date date;
    private String location;

    public UserDetailsTransport getSender() {
        return sender;
    }

    public void setSender(UserDetailsTransport sender) {
        this.sender = sender;
    }

    public UserDetailsTransport getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDetailsTransport receiver) {
        this.receiver = receiver;
    }

    public GroupTransport getGroupReceiver() {
        return groupReceiver;
    }

    public void setGroupReceiver(GroupTransport groupReceiver) {
        this.groupReceiver = groupReceiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
