package points.facade;

import points.group.GroupFacade;
import points.group.GroupService;
import points.group.dto.Group;
import points.strategy.StrategyFacade;
import points.transport.group.GroupDetailsTransport;
import points.transport.user.UserTransport;
import points.transport.group.GroupTransport;
import points.user.UserFacade;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aardelean on 13.12.2014.
 */
@Stateless
public class GroupFacadeImpl implements GroupFacade{

    @EJB
    private GroupService groupService;

    @EJB
    private StrategyFacade strategyFacade;

    @EJB
    private UserFacade userFacade;

    @Override
    public GroupTransport addGroup(GroupTransport group) {
        List<Long> friendIds = null;
        Group result = groupService.addGroup(friendIds,group.getCurrentUserId(), group.getName());
        return convert(result);
    }

    @Override
    public GroupTransport changeStatus(GroupTransport group) {
        return null;
    }

    @Override
    public GroupTransport addUserStatus(GroupTransport group) {
        return null;
    }

    @Override
    public void removeGroup(GroupTransport group) {

    }

    private void populateGroup(GroupTransport transport, Group group){
        transport.setName(group.getName());
        transport.setId(group.getId());
        transport.setUserStatusTransport(userFacade.convertUserStatusTransport(group.getStatus()));
    }

    private GroupTransport convert(Group group){
        GroupTransport result = new GroupTransport();
        populateGroup(result, group);
        return result;
    }

    private GroupDetailsTransport convertDetails(Group group){
        GroupDetailsTransport result = new GroupDetailsTransport();
        populateGroup(result,group);
        result.setName(group.getName());
        result.setId(group.getId());
        List<User> users = group.getContacts();
        for(User user:users){
            UserTransport friend = new UserTransport();
            friend.setId(user.getId());
            friend.setFirstName(user.getFirstName());
            friend.setLastName(user.getLastName());
            friend.setSocialProvider(user.getSocialProvider().name());
        }
        result.setFriends(new ArrayList<UserTransport>());

        return result;
    }
}
