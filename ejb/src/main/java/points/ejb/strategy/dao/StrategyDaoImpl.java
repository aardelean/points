package points.ejb.strategy.dao;

import points.ejb.dao.GenericDaoImpl;
import points.strategy.dao.StrategyDao;
import points.strategy.dto.LocationBasedStrategy;
import points.strategy.dto.Strategy;
import points.strategy.dto.TimeBasedStrategy;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 19.10.2014.
 */
@Stateless
public class StrategyDaoImpl extends GenericDaoImpl<Strategy> implements StrategyDao{

    @Override
    public TimeBasedStrategy findTimeBasedStrategyById(Long id) {
        return em.find(TimeBasedStrategy.class,id);
    }

    @Override
    public LocationBasedStrategy findLocationBasedStrategyById(Long id) {
        return em.find(LocationBasedStrategy.class,id);
    }
}
