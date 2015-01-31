package points.strategy;

import org.junit.Assert;
import org.junit.Test;
import points.AbstractEEDeployment;
import points.strategy.dto.LocationBasedStrategy;
import points.strategy.dto.LocationType;
import points.strategy.dto.TimeBasedStrategy;
import points.strategy.dto.TimeStrategyType;
import points.user.dto.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by aardelean on 22.11.2014.
 */
public class StrategyServiceTest extends AbstractEEDeployment{

    private static final Long USER_ID=100l;

    @Inject
    private StrategyService strategyService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createDailyTimeStrategy(){
        User creator = em.find(User.class, USER_ID);
        TimeBasedStrategy inserted = strategyService.createDailyTimeStrategy(creator, new Date(), new Date());
        TimeBasedStrategy found = em.find(TimeBasedStrategy.class, inserted.getId());
        Assert.assertEquals(TimeStrategyType.DAILY,found.getTimeStrategyType());
    }

    @Test
    public void createWeeklyTimeStrategy(){
        User creator = em.find(User.class, USER_ID);
        TimeBasedStrategy inserted = strategyService.createWeeklyTimeStrategy(creator, new Date(), new Date());
        TimeBasedStrategy found = em.find(TimeBasedStrategy.class, inserted.getId());
        Assert.assertEquals(TimeStrategyType.WEEKLY,found.getTimeStrategyType());
    }

    @Test
    public void createMonthlyTimeStrategy(){
        User creator = em.find(User.class, USER_ID);
        TimeBasedStrategy inserted = strategyService.createMonthlyTimeStrategy(creator, new Date(), new Date());
        TimeBasedStrategy found = em.find(TimeBasedStrategy.class, inserted.getId());
        Assert.assertEquals(TimeStrategyType.MONTHLY,found.getTimeStrategyType());
    }

    @Test
    public void createNonRepeatableTimeStrategy(){
        User creator = em.find(User.class, USER_ID);
        TimeBasedStrategy inserted = strategyService.createNonRepeatableTimeStrategy(creator, new Date(), new Date());
        TimeBasedStrategy found = em.find(TimeBasedStrategy.class, inserted.getId());
        Assert.assertEquals(TimeStrategyType.NON_REPEATABLE,found.getTimeStrategyType());
    }

    @Test
     public void createCityLocationStrategy(){
        User creator = em.find(User.class, USER_ID);
        LocationBasedStrategy inserted = strategyService.createCityLocationStrategy(creator, "BRASOV");
        LocationBasedStrategy found = em.find(LocationBasedStrategy.class, inserted.getId());
        Assert.assertEquals("BRASOV",found.getLocationName());
        Assert.assertEquals(LocationType.CITY,found.getLocationType());
    }

    @Test
    public void createCountryLocationStrategy(){
        User creator = em.find(User.class, USER_ID);
        LocationBasedStrategy inserted = strategyService.createCountryLocationStrategy(creator, "ROMANIA");
        LocationBasedStrategy found = em.find(LocationBasedStrategy.class, inserted.getId());
        Assert.assertEquals("ROMANIA",found.getLocationName());
        Assert.assertEquals(LocationType.COUNTRY,found.getLocationType());
    }

}
