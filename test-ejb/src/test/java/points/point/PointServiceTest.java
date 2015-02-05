package points.point;

import java.util.Date;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import points.AbstractEEDeployment;
import points.point.dto.Point;

/**
 * Created by aardelean on 02.02.2015.
 */
public class PointServiceTest extends AbstractEEDeployment {

	@EJB
	private PointService classUnderTest;

	@Test
	public void testStore(){
		Point original = buildPoint();
		classUnderTest.save(original);
		Point resulted = classUnderTest.getPointByUserId(1l);
		Assert.assertEquals(original.getLatitude(),resulted.getLatitude());
		Assert.assertEquals(original.getTime(),resulted.getTime());
		Assert.assertEquals(original.getLongitude(),resulted.getLongitude());
		Assert.assertEquals(original.getUserId(),resulted.getUserId());
		Assert.assertEquals(original.getId(), resulted.getId());
	}
	public static Point buildPoint(){
		Point point = new Point();
		point.setUserId(1l);
		point.setTime(new Date());
		point.setLongitude(100D);
		point.setLongitude(100D);
		return point;
	}
}
