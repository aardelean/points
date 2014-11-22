package points.ejb.strategy;

import points.group.dao.GroupDao;
import points.group.dto.Group;
import points.strategy.StrategyService;
import points.strategy.dao.LocationBasedStrategyDao;
import points.strategy.dao.StrategyDao;
import points.strategy.dao.TimeBasedStrategyDao;
import points.strategy.dto.*;

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
    private TimeBasedStrategyDao timeBasedStrategyDao;

    @EJB
    private LocationBasedStrategyDao locationBasedStrategyDao;

    @EJB
    private StrategyDao strategyDao;

    @EJB
    private GroupDao groupDao;

    @Inject
    private StrategyFactory strategyFactory;

    @Override
    public void createDailyTimeStrategy(Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(startDate,endDate, TimeStrategyType.DAILY);
        timeBasedStrategyDao.save(timeBasedStrategy);
    }

    @Override
    public void createWeeklyTimeStrategy(Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(startDate,endDate, TimeStrategyType.WEEKLY);
        timeBasedStrategyDao.save(timeBasedStrategy);
    }

    @Override
    public void createMonthlyTimeStrategy(Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(startDate,endDate, TimeStrategyType.MONTHLY);
        timeBasedStrategyDao.save(timeBasedStrategy);
    }



    @Override
    public void createNonRepeatableTimeStrategy(Date startDate, Date endDate) {
        TimeBasedStrategy timeBasedStrategy = strategyFactory.createTimeBasedStrategy(startDate, endDate, TimeStrategyType.NON_REPEATABLE);
        timeBasedStrategyDao.save(timeBasedStrategy);
    }



    @Override
    public void createCityLocationStrategy(String locationName) {
        LocationBasedStrategy locationBasedStrategy = strategyFactory.createLocationBasedStrategy(locationName,LocationType.CITY);
        locationBasedStrategyDao.save(locationBasedStrategy);
    }

    @Override
    public void createCountryLocationStrategy(String locationName) {
        LocationBasedStrategy locationBasedStrategy = strategyFactory.createLocationBasedStrategy(locationName,LocationType.COUNTRY);
        locationBasedStrategyDao.save(locationBasedStrategy);
    }



    @Override
    public void deleteTimeStrategy(Long strategyId) {
        Strategy strategy = strategyDao.findById(strategyId);
        TimeBasedStrategy timeBasedStrategy = timeBasedStrategyDao.findById(strategy);
        timeBasedStrategyDao.delete(timeBasedStrategy);
    }

    @Override
    public void deleteLocationStrategy(Long strategyId) {
        Strategy strategy = strategyDao.findById(strategyId);
        LocationBasedStrategy locationBasedStrategy = locationBasedStrategyDao.findById(strategy);
        locationBasedStrategyDao.delete(locationBasedStrategy);
    }

    @Override
    public void updateTimeStrategy(Long strategyId, Date startDate, Date endDate, String timeStrategyType) {
        TimeBasedStrategy timeBasedStrategy = timeBasedStrategyDao.findById(strategyId);
        TimeStrategyType type = TimeStrategyType.valueOf(timeStrategyType);
        timeBasedStrategy.setStartTime(startDate);
        timeBasedStrategy.setStartTime(endDate);
        timeBasedStrategy.setTimeStrategyType(type);
        timeBasedStrategyDao.update(timeBasedStrategy);
    }

    @Override
    public void updateLocationStrategy(Long strategyId, String location, String locationType) {
        LocationBasedStrategy locationBasedStrategy = locationBasedStrategyDao.findById(strategyId);
        LocationType type = LocationType.valueOf(locationType);
        locationBasedStrategy.setLocationName(location);
        locationBasedStrategy.setLocationType(type);
        locationBasedStrategyDao.update(locationBasedStrategy);
    }

    @Override
    public void disableStrategy(Long strategyId) {

    }

    @Override
    public void enableStrategy(Long strategyId) {
        Strategy strategy = strategyDao.findById(strategyId);
        strategy.setEnabled(true);
        strategyDao.update(strategy);
    }

    @Override
    public void addGroups(Long strategyId, List<Long> groupIds) {
        Strategy strategy = strategyDao.findById(strategyId);
        for(Long groupId : groupIds){
            Group group = groupDao.findById(groupId);
            strategy.getGroups().add(group);
            group.setStrategy(strategy);

        }
        strategyDao.update(strategy);
    }

    @Override
    public void removeGroups(Long strategyId, List<Long> groupIds) {
        Strategy strategy = strategyDao.findById(strategyId);
        for(Long groupId : groupIds){
            Group group = groupDao.findById(groupId);
            strategy.getGroups().remove(group);
            group.setStrategy(null);
            groupDao.update(group);

        }
        strategyDao.update(strategy);
    }
}
