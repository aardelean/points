package points.strategy.dao;

import points.GenericDao;
import points.strategy.dto.LocationBasedStrategy;
import points.strategy.dto.Strategy;
import points.strategy.dto.TimeBasedStrategy;

/**
 * Created by aardelean on 19.10.2014.
 */
public interface StrategyDao extends GenericDao<Strategy> {

    TimeBasedStrategy findTimeBasedStrategyById(Long id);

    LocationBasedStrategy findLocationBasedStrategyById(Long id);
}
