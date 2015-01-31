package points.ejb.strategy;

import points.ejb.group.service.GroupFactory;
import points.strategy.dto.*;
import points.user.dto.User;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created by aardelean on 19.10.2014.
 */

@Singleton
public class StrategyFactory {

    @Inject
    private GroupFactory groupFactory;

    public LocationBasedStrategy createLocationBasedStrategy(User user,String locationName, LocationType locationType){
        LocationBasedStrategy strategy = new LocationBasedStrategy();
        strategy.setUserStatus(groupFactory.createUserStatus(user));
        strategy.setLocationType(locationType);
        strategy.setLocationName(locationName);
        return strategy;
    }


    public TimeBasedStrategy createTimeBasedStrategy(User user,Date startDate, Date endDate, TimeStrategyType strategyType){
        TimeBasedStrategy strategy = new TimeBasedStrategy();
        strategy.setUserStatus(groupFactory.createUserStatus(user));
        strategy.setEndTime(endDate);
        strategy.setStartTime(startDate);
        strategy.setTimeStrategyType(strategyType);
        return strategy;
    }
}
