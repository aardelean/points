package points.group.dao;

import points.GenericDao;
import points.group.dto.Group;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
@Local
public interface GroupDao extends GenericDao<Group> {

    List<Group> findGroupByUserId(Long userId);

}
