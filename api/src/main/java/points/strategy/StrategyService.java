package points.strategy;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 * Created by aardelean on 18.10.2014.
 */

@Local
public interface StrategyService {

    void createDailyTimeStrategy(Date startDate, Date endDate);

    void createWeeklyTimeStrategy(Date startDate, Date endDate);

    void createMonthlyTimeStrategy(Date startDate, Date endDate);

    void createNonRepeatableTimeStrategy(Date startDate, Date endDate);

    void createCityLocationStrategy(String locationName);

    void createCountryLocationStrategy(String locationName);

    void deleteTimeStrategy(Long strategyId);

    void deleteLocationStrategy(Long strategyId);

    void updateTimeStrategy(Long strategyId, Date startDate, Date endDate, String timeStrategyType);

    void updateLocationStrategy(Long strategyId, String location, String locationType);

    void disableStrategy(Long strategyId);

    void enableStrategy(Long strategyId);

    void addGroups(Long strategyId, List<Long> groupIds);

    void removeGroups(Long strategyId, List<Long> groupIds);
}
