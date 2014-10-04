package points;

import org.junit.Assert;
import org.junit.Test;
import points.dao.points.PointCacheDaoLocal;
import points.dto.point.Point;

import javax.inject.Inject;

/**
 * Created by aardelean on 30.09.2014.
 */
public class PointsCacheDaoTest extends AbstractEEDeployment{
    @Inject
    private PointCacheDaoLocal pointsCacheDao;


    @Test
    public void test(){
        Assert.assertNotNull(pointsCacheDao);
    }

    @Test
    public void testCache(){
        Point point = new Point();
        point.setLatitude("latitude");
        point.setLongitude("longitude");
        pointsCacheDao.store(point, 11L);
        point = pointsCacheDao.get(11L);
        Assert.assertEquals("latitude",point.getLatitude());
        Assert.assertEquals("longitude",point.getLongitude());
    }

}
