package points.ejb.group.dao;

import points.group.dao.GroupDao;
import points.group.dto.Group;
import points.ejb.dao.GenericDaoImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by aardelean on 04.10.2014.
 */
@Stateless
public class GroupDaoImpl extends GenericDaoImpl<Group> implements GroupDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Group> findGroupByUserId(Long userId) {
        Query query = em.createQuery("FROM Group WHERE creator.id=:creatorId");
        query.setParameter("creatorId",userId);
        return query.getResultList();
    }
}