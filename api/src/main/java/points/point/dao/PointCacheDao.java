package points.point.dao;

import javax.ejb.Local;

import points.point.dto.Point;

/**
 * Created by aardelean on 28.09.2014.
 */
@Local
public interface PointCacheDao {

    void store(Point point);

    Point get(Long userId);
}
