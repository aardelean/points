package points.ejb.strategy.dao;

import points.ejb.dao.GenericDaoImpl;
import points.strategy.dao.LocationBasedStrategyDao;
import points.strategy.dto.LocationBasedStrategy;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 19.10.2014.
 */
@Stateless
public class LocationBasedStrategyDaoImpl extends GenericDaoImpl<LocationBasedStrategy> implements LocationBasedStrategyDao {
}
