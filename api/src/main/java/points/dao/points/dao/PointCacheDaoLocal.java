package points.dao.points.dao;

import points.dao.points.dto.Point;

import javax.ejb.Local;

/**
 * Created by aardelean on 28.09.2014.
 */
@Local
public interface PointCacheDaoLocal {

    void store(Point point, Long userId);

    Point get(Long userId);
}
