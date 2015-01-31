package points.transport.group;

import points.transport.AbstractTransportEntity;
import points.transport.user.UserStatusTransport;

import java.io.Serializable;

/**
 * Created by aardelean on 13.12.2014.
 */
public class GroupTransport extends AbstractTransportEntity implements Serializable {

    private String name;

    private UserStatusTransport userStatusTransport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatusTransport getUserStatusTransport() {
        return userStatusTransport;
    }

    public void setUserStatusTransport(UserStatusTransport userStatusTransport) {
        this.userStatusTransport = userStatusTransport;
    }
}
