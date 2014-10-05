package points.ejb.group;

import points.dao.group.GroupService;

import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
public class GroupServiceImpl implements GroupService {
    @Override
    public void addGroup(List<Long> friendIds, String creatorId, String name, Boolean enabled, String userStatusType, Integer pingTime) {

    }

    @Override
    public void addGroup(List<Long> friendIds, String creatorId, String name, Boolean enabled, String userStatusType) {

    }

    @Override
    public void addGroup(List<Long> friendIds, String creatorId, String name, String userStatusType) {

    }

    @Override
    public void addGroup(List<Long> friendIds, String creatorId, String name, Boolean enabled) {

    }

    @Override
    public void addGroup(List<Long> friendIds, String creatorId, String name) {

    }
}
