package points.point;

import javax.ejb.Local;

import points.transport.point.PointTransport;

/**
 * Created by aardelean on 13.12.2014.
 */
@Local
public interface PointFacade {

	void save(PointTransport pointTransport);

	PointTransport getPoint(Long userId);
}
