package points.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import points.point.PointFacade;
import points.point.PointService;
import points.point.dto.Point;
import points.transport.point.PointTransport;

/**
 * Created by aardelean on 31.01.2015.
 */
@Stateless
@Path("/point")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointFacadeImpl implements PointFacade{

	@EJB
	private PointService pointService;

	@Override
	@PUT
	public void save(PointTransport pointTransport) {
		pointService.save(convert(pointTransport));
	}

	@Override
	@GET
	@Path("/{userId}")
	public PointTransport getPoint(@PathParam("userId")Long userId) {
		return convert(pointService.getPointByUserId(userId));
	}

	private PointTransport convert(Point point){
		PointTransport result = new PointTransport();
		result.setLatitude(point.getLatitude());
		result.setLongitude(point.getLongitude());
		result.setTime(point.getTime());
		result.setUserId(point.getUserId());
		return result;
	}
	private Point convert(PointTransport point){
		Point result = new Point();
		result.setLatitude(point.getLatitude());
		result.setLongitude(point.getLongitude());
		result.setTime(point.getTime());
		result.setUserId(point.getCurrentUserId());
		return result;
	}
}
