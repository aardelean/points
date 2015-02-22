package points.point;

import javax.ejb.Local;

import points.point.dto.Point;

/**
 * Created by aardelean on 31.01.2015.
 */
@Local
public interface PointService {

	void save(Point point);

	Point getPointByUserId(Long userId);
}
