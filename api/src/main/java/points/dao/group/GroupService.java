package points.dao.group;

import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
public interface GroupService {

    void addGroup(List<Long> friendIds, String creatorId, String name, Boolean enabled, String userStatusType, Integer pingTime);

    void addGroup(List<Long> friendIds, String creatorId, String name, Boolean enabled, String userStatusType);

    void addGroup(List<Long> friendIds, String creatorId, String name, String userStatusType);

    void addGroup(List<Long> friendIds, String creatorId, String name, Boolean enabled);

    void addGroup(List<Long> friendIds, String creatorId, String name);
}
