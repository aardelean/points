package points.group;

import points.transport.group.GroupTransport;

import javax.ejb.Local;

/**
 * Created by aardelean on 13.12.2014.
 */
@Local
public interface GroupFacade {

    GroupTransport addGroup(GroupTransport group);

    GroupTransport changeStatus(GroupTransport group);

    GroupTransport addUserStatus(GroupTransport group);

    void removeGroup(GroupTransport group);
}
