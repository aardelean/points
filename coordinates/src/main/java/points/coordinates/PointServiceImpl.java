package points.coordinates;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import points.point.PointService;
import points.point.dao.PointCacheDao;
import points.point.dto.Point;

/**
 * Created by aardelean on 31.01.2015.
 */
@Stateless
public class PointServiceImpl implements PointService{

	@EJB
	private PointCacheDao pointCacheDao;

	@Override
	public void save(Point point) {
		pointCacheDao.store(point);
	}

	@Override
	public Point getPointByUserId(Long userId) {
		return pointCacheDao.get(userId);
	}
}
