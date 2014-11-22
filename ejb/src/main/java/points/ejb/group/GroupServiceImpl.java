package points.ejb.group;

import points.group.GroupService;
import points.group.dao.GroupDao;
import points.group.dao.UserStatusDao;
import points.group.dto.Group;
import points.group.dto.UserStatus;
import points.group.dto.UserStatusType;
import points.user.dao.UserDao;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
@Stateless
public class GroupServiceImpl implements GroupService {

    @Inject
    private GroupFactory groupFactory;
    @EJB
    private UserDao userDao;

    @EJB
    private GroupDao groupDao;

    @EJB
    private UserStatusDao userStatusDao;

    @Override
    public void addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType, Integer pingTime) {
        User creator = userDao.findById(creatorId);
        Group group = groupFactory.createGroup(creator, name, friendIds);
        group.setEnabled(enabled);
        UserStatusType statusType = null;
        if(userStatusType!=null){
            statusType = UserStatusType.valueOf(userStatusType);
        }else{
            statusType = groupFactory.defaultType;
        }
        UserStatus userStatus = groupFactory.createUserStatus(creator, statusType, pingTime);
        userStatusDao.save(userStatus);
        group.setStatus(userStatus);

        groupDao.save(group);
    }

    @Override
    public void addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType) {
        addGroup(friendIds,creatorId,name, enabled,userStatusType,groupFactory.defaultPingTime);
    }

    @Override
    public void addGroup(List<Long> friendIds, Long creatorId, String name, String userStatusType) {
        addGroup(friendIds,creatorId,name, groupFactory.defaultEnabled,userStatusType,groupFactory.defaultPingTime);
    }

    @Override
    public void addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled) {
        addGroup(friendIds,creatorId,name, enabled, null,groupFactory.defaultPingTime);
    }

    @Override
    public void addGroup(List<Long> friendIds, Long creatorId, String name) {
        addGroup(friendIds,creatorId,name, groupFactory.defaultEnabled, null,groupFactory.defaultPingTime);
    }

    @Override
    public void changeStatus(Long userStatusId, Long groupId) {
        Group group = groupDao.findById(groupId);
        UserStatus status = userStatusDao.findById(userStatusId);
        group.setStatus(status);
        status.getGroups().add(group);
        groupDao.update(group);
        userStatusDao.update(status);
    }

    @Override
    public void changeStatus(Long creatorId, UserStatusType userStatusType, Long groupId) {
        Group group = groupDao.findById(groupId);
        UserStatus status = createUserStatus(creatorId, userStatusType);
        status.setGroups(new ArrayList<Group>());
        status.getGroups().add(group);
        userStatusDao.update(status);
        group.setStatus(status);
        groupDao.update(group);
    }

    @Override
    public void addUserStatus(Long creatorId, UserStatusType userStatusType) {
        createUserStatus(creatorId, userStatusType);
    }

    private UserStatus createUserStatus(Long creatorId, UserStatusType userStatusType){
        User creator = userDao.findById(creatorId);
        UserStatus status = groupFactory.createUserStatus(creator, userStatusType);
        userStatusDao.save(status);
        return status;
    }


}
