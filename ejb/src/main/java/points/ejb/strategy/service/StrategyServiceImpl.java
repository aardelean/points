package points.ejb.strategy.service;

import points.ejb.strategy.StrategyFactory;
import points.group.dao.GroupDao;
import points.group.dto.Group;
import points.strategy.StrategyService;
import points.strategy.dao.StrategyDao;
import points.strategy.dto.*;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by aardelean on 19.10.2014.
 */
@Stateless
public class StrategyServiceImpl implements StrategyService {

    @EJB
    private StrategyDao strategyDao;

    @EJB
    private GroupDao groupDao;

    @Inject
    private StrategyFactory strategyFactory;

    @Override
    public TimeBasedStrategy createDailyTimeStrategy(User creator,Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(creator,startDate,endDate, TimeStrategyType.DAILY);
        strategyDao.save(timeBasedStrategy);
        return timeBasedStrategy;
    }

    @Override
    public TimeBasedStrategy createWeeklyTimeStrategy(User creator,Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(creator,startDate,endDate, TimeStrategyType.WEEKLY);
        strategyDao.save(timeBasedStrategy);
        return timeBasedStrategy;
    }

    @Override
    public TimeBasedStrategy createMonthlyTimeStrategy(User creator,Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(creator,startDate,endDate, TimeStrategyType.MONTHLY);
        strategyDao.save(timeBasedStrategy);
        return timeBasedStrategy;
    }



    @Override
    public TimeBasedStrategy createNonRepeatableTimeStrategy(User creator,Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(creator,startDate, endDate, TimeStrategyType.NON_REPEATABLE);
        strategyDao.save(timeBasedStrategy);
        return timeBasedStrategy;
    }



    @Override
    public LocationBasedStrategy createCityLocationStrategy(User creator,String locationName) {
        LocationBasedStrategy locationBasedStrategy = strategyFactory.createLocationBasedStrategy(creator,locationName,LocationType.CITY);
        strategyDao.save(locationBasedStrategy);
        return locationBasedStrategy;
    }

    @Override
    public LocationBasedStrategy createCountryLocationStrategy(User creator,String locationName) {
        LocationBasedStrategy locationBasedStrategy = strategyFactory.createLocationBasedStrategy(creator,locationName,LocationType.COUNTRY);
        strategyDao.save(locationBasedStrategy);
        return locationBasedStrategy;
    }

    @Override
    public void removeTimeStrategy(Long strategyId) {
        TimeBasedStrategy timeBasedStrategy = strategyDao.findTimeBasedStrategyById(strategyId);
        strategyDao.delete(timeBasedStrategy);
    }

    @Override
    public void removeLocationStrategy(Long strategyId) {
        LocationBasedStrategy locationBasedStrategy = strategyDao.findLocationBasedStrategyById(strategyId);
        strategyDao.delete(locationBasedStrategy);
    }

    @Override
    public TimeBasedStrategy updateTimeStrategy(Long strategyId, Date startDate, Date endDate, String timeStrategyType) {
        TimeBasedStrategy timeBasedStrategy = strategyDao.findTimeBasedStrategyById(strategyId);
        TimeStrategyType type = TimeStrategyType.valueOf(timeStrategyType);
        timeBasedStrategy.setStartTime(startDate);
        timeBasedStrategy.setStartTime(endDate);
        timeBasedStrategy.setTimeStrategyType(type);
        strategyDao.update(timeBasedStrategy);
        return timeBasedStrategy;
    }

    @Override
    public LocationBasedStrategy updateLocationStrategy(Long strategyId, String location, String locationType) {
        LocationBasedStrategy locationBasedStrategy = strategyDao.findLocationBasedStrategyById(strategyId);
        LocationType type = LocationType.valueOf(locationType);
        locationBasedStrategy.setLocationName(location);
        locationBasedStrategy.setLocationType(type);
        strategyDao.update(locationBasedStrategy);
        return locationBasedStrategy;
    }

    @Override
    public Strategy disableStrategy(Long strategyId) {
        return strategyEnabled(strategyId,false);
    }

    @Override
    public Strategy enableStrategy(Long strategyId) {
       return strategyEnabled(strategyId,true);
    }

    private Strategy strategyEnabled(Long strategyId, boolean isEnabled){
        Strategy strategy = strategyDao.findById(strategyId);
        strategy.setEnabled(isEnabled);
        strategyDao.update(strategy);
        return strategy;
    }

    @Override
    public Strategy addGroups(Long strategyId, List<Long> groupIds) {
        Strategy strategy = strategyDao.findById(strategyId);
        for(Long groupId : groupIds){
            Group group = groupDao.findById(groupId);
            strategy.getGroups().add(group);
            group.getStrategies().add(strategy);

        }
        strategyDao.update(strategy);
        return strategy;
    }

    @Override
    public Strategy removeGroups(Long strategyId, List<Long> groupIds) {
        Strategy strategy = strategyDao.findById(strategyId);
        for(Long groupId : groupIds){
            Group group = groupDao.findById(groupId);
            strategy.getGroups().remove(group);
            groupDao.update(group);
        }
        strategyDao.update(strategy);
        return strategy;
    }
}
