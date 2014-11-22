package points.group;

import points.group.dto.UserStatusType;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
@Local
public interface GroupService {

    void addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType, Integer pingTime);

    void addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled, String userStatusType);

    void addGroup(List<Long> friendIds, Long creatorId, String name, String userStatusType);

    void addGroup(List<Long> friendIds, Long creatorId, String name, Boolean enabled);

    void addGroup(List<Long> friendIds, Long creatorId, String name);

    void changeStatus(Long userStatus, Long groupId);

    void changeStatus(Long creatorId, UserStatusType userStatusType, Long groupId);

    void addUserStatus(Long creatorId, UserStatusType userStatusType);

}
