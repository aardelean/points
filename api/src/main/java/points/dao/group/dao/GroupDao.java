package points.dao.group.dao;

import points.dao.GenericDao;
import points.dao.group.dto.Group;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
@Local
public interface GroupDao extends GenericDao<Group> {

    List<Group> findGroupByUserId(Long userId);


}
