package points.ejb.strategy;

import points.strategy.dto.*;

import javax.ejb.Singleton;
import java.util.Date;

/**
 * Created by aardelean on 19.10.2014.
 */

@Singleton
public class StrategyFactory {

    public LocationBasedStrategy createLocationBasedStrategy(String locationName, LocationType locationType){
        Strategy strategy = new Strategy();
        strategy.setStrategyType(StrategyType.LOCATION);
        LocationBasedStrategy locationBasedStrategy = new LocationBasedStrategy();
        locationBasedStrategy.setLocationType(locationType);
        locationBasedStrategy.setStrategy(strategy);
        locationBasedStrategy.setLocationName(locationName);
        return locationBasedStrategy;
    }


    public TimeBasedStrategy createTimeBasedStrategy(Date startDate, Date endDate, TimeStrategyType strategyType){
        Strategy strategy = new Strategy();
        strategy.setStrategyType(StrategyType.TIME);
        TimeBasedStrategy timeBasedStrategy = new TimeBasedStrategy();
        timeBasedStrategy.setEndTime(endDate);
        timeBasedStrategy.setStartTime(startDate);
        timeBasedStrategy.setTimeStrategyType(strategyType);
        timeBasedStrategy.setStrategy(strategy);
        return timeBasedStrategy;
    }
}
