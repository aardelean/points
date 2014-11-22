package points.ejb.strategy.dao;

import points.ejb.dao.GenericDaoImpl;
import points.strategy.dao.TimeBasedStrategyDao;
import points.strategy.dto.TimeBasedStrategy;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 19.10.2014.
 */
@Stateless
public class TimeBasedStrategyDaoImpl extends GenericDaoImpl<TimeBasedStrategy> implements TimeBasedStrategyDao {
}
