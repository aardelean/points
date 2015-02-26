package points.ejb.group.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import points.ejb.dao.GenericDaoImpl;
import points.group.dao.GroupDao;
import points.group.dto.Group;

/**
 * Created by aardelean on 04.10.2014.
 */
@Stateless
public class GroupDaoImpl extends GenericDaoImpl<Group> implements GroupDao{

    @Override
    public List<Group> findGroupByUserId(Long userId) {
        Query query = em.createQuery("FROM Group WHERE creator.id=:creatorId");
        query.setParameter("creatorId",userId);
        return query.getResultList();
    }
}
