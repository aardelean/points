package points.transport.user;

import points.transport.AbstractTransportEntity;
import points.transport.group.GroupTransport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aardelean on 13.12.2014.
 */
public class UserStatusTransport extends AbstractTransportEntity implements Serializable {
    private String name;
    private List<GroupTransport> groups;
    private String status;
    private Integer pingTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupTransport> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupTransport> groups) {
        this.groups = groups;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPingTime() {
        return pingTime;
    }

    public void setPingTime(Integer pingTime) {
        this.pingTime = pingTime;
    }
}
