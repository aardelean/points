package points.coordinates;

//import org.jboss.as.clustering.infinispan.DefaultCacheContainer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import points.point.dao.PointCacheDao;
import points.point.dto.Point;

/**
 * Created by aardelean on 28.09.2014.
 */
@Stateless
public class PointCacheDaoImpl implements PointCacheDao {


	@PersistenceContext(unitName = "volatile")
	private EntityManager em;

    @Override
	@Transactional
    public void store(Point point) {
        em.persist(point);
    }

    @Override
	@Transactional
    public Point get(Long userId) {
        Query query =  em.createQuery("FROM Point WHERE userId=:userId", Point.class);
		query.setParameter("userId",userId);
		query.setMaxResults(1);
		return  (Point)query.getSingleResult();
    }

}
