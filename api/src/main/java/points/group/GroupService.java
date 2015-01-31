package points.group;

import points.group.dto.Group;
import points.group.dto.UserStatus;
import points.group.dto.UserStatusType;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
@Local
public interface GroupService {

    Group addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType, Integer pingTime);

    Group addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType);

    Group addGroup(List<Long> friendIds, Long creatorId, String name, String userStatusType);

    Group addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled);

    Group addGroup(List<Long> friendIds, Long creatorId, String name);

    Group changeStatus(Long userStatus, Long groupId);

    Group changeStatus(Long creatorId, UserStatusType userStatusType, Long groupId);

    UserStatus addUserStatus(Long creatorId, UserStatusType userStatusType);

    void removeGroup(Long groupId);

}
