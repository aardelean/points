package points.ejb.group.service;

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
    public Group addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType, Integer pingTime) {
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
        return group;
    }

    @Override
    public Group addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType) {
        return addGroup(friendIds,creatorId,name, enabled,userStatusType,groupFactory.defaultPingTime);
    }

    @Override
    public Group addGroup(List<Long> friendIds, Long creatorId, String name, String userStatusType) {
        return addGroup(friendIds,creatorId,name, groupFactory.defaultEnabled,userStatusType,groupFactory.defaultPingTime);
    }

    @Override
    public Group addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled) {
        return addGroup(friendIds,creatorId,name, enabled, null,groupFactory.defaultPingTime);
    }

    @Override
    public Group addGroup(List<Long> friendIds, Long creatorId, String name) {
        return addGroup(friendIds,creatorId,name, groupFactory.defaultEnabled, null,groupFactory.defaultPingTime);
    }

    @Override
    public Group changeStatus(Long userStatusId, Long groupId) {
        Group group = groupDao.findById(groupId);
        UserStatus status = userStatusDao.findById(userStatusId);
        group.setStatus(status);
        status.getGroups().add(group);
        groupDao.update(group);
        userStatusDao.update(status);
        return group;
    }

    @Override
    public Group changeStatus(Long creatorId, UserStatusType userStatusType, Long groupId) {
        Group group = groupDao.findById(groupId);
        UserStatus status = group.getStatus();
        if(status==null || status.getGroups().size()>1){
            status = createUserStatus(creatorId, userStatusType);
        }else{
            status.setType(userStatusType);
        }
        status.setGroups(new ArrayList<Group>());
        status.getGroups().add(group);
        userStatusDao.update(status);
        group.setStatus(status);
        groupDao.update(group);
        return group;
    }

    @Override
    public UserStatus addUserStatus(Long creatorId, UserStatusType userStatusType) {
        return createUserStatus(creatorId, userStatusType);
    }

    @Override
    public void removeGroup(Long groupId) {
        Group group = groupDao.findById(groupId);
        groupDao.delete(group);
    }

    private UserStatus createUserStatus(Long creatorId, UserStatusType userStatusType){
        User creator = userDao.findById(creatorId);
        UserStatus status = groupFactory.createUserStatus(creator, userStatusType);
        userStatusDao.save(status);
        return status;
    }

}
