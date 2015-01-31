package points.strategy;

import points.strategy.dto.LocationBasedStrategy;
import points.strategy.dto.Strategy;
import points.strategy.dto.TimeBasedStrategy;
import points.user.dto.User;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 * Created by aardelean on 18.10.2014.
 */

@Local
public interface StrategyService {

    TimeBasedStrategy createDailyTimeStrategy(User creator,Date startDate, Date endDate);

    TimeBasedStrategy createWeeklyTimeStrategy(User creator,Date startDate, Date endDate);

    TimeBasedStrategy createMonthlyTimeStrategy(User creator,Date startDate, Date endDate);

    TimeBasedStrategy createNonRepeatableTimeStrategy(User creator,Date startDate, Date endDate);

    LocationBasedStrategy createCityLocationStrategy(User creator,String locationName);

    LocationBasedStrategy createCountryLocationStrategy(User creator,String locationName);

    void removeTimeStrategy(Long strategyId);

    void removeLocationStrategy(Long strategyId);

    TimeBasedStrategy updateTimeStrategy(Long strategyId, Date startDate, Date endDate, String timeStrategyType);

    LocationBasedStrategy updateLocationStrategy(Long strategyId, String location, String locationType);

    Strategy disableStrategy(Long strategyId);

    Strategy enableStrategy(Long strategyId);

    Strategy addGroups(Long strategyId, List<Long> groupIds);

    Strategy removeGroups(Long strategyId, List<Long> groupIds);
}
