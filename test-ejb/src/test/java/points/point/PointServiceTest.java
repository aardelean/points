package points.point;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.junit.Assert;
import org.junit.Test;

import points.AbstractEEDeployment;
import points.point.dto.Point;

/**
 * Created by aardelean on 02.02.2015.
 */
public class PointServiceTest extends AbstractEEDeployment {

	@Inject
	private PointService classUnderTest;

	@PersistenceContext(unitName = "volatile")
	private EntityManager em;

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
		point.setLatitude(48.138593);
		point.setLongitude(11.504076);
		return point;
	}

	@Test
	@Transactional
	public void testRangeTest(){
		classUnderTest.save(buildPoint());
		FullTextSession session = getFullTextSession();
		QueryBuilder builder = session.getSearchFactory().buildQueryBuilder().forEntity( Point.class ).get();

		org.apache.lucene.search.Query luceneQuery = builder
				.spatial()
				.within( 10D, Unit.KM )
				.ofLatitude( 48.142627 )
				.andLongitude( 11.519161 )
				.createQuery();
		FullTextQuery fullTextQuery = session.createFullTextQuery( luceneQuery, Point.class);
		fullTextQuery.setProjection(FullTextQuery.SPATIAL_DISTANCE, FullTextQuery.THIS);
		List results = fullTextQuery.list();
		Assert.assertEquals(Integer.valueOf(1) , (Integer)results.size());
		Assert.assertEquals(Long.valueOf(1) , ((Point)results.get(0)).getUserId());

		luceneQuery = builder
				.spatial()
				.within( 1D, Unit.KM )
				.ofLatitude( 48.154024 )
				.andLongitude( 11.527035 )
				.createQuery();

		fullTextQuery = session.createFullTextQuery(luceneQuery, Point.class);
		results = fullTextQuery.list();
		Assert.assertTrue(results.isEmpty());
	}

	private FullTextSession getFullTextSession(){
		FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession((Session)em.getDelegate());
		return fullTextSession;
	}
}
