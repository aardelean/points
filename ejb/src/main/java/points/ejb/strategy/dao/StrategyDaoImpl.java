package points.ejb.strategy.dao;

import points.ejb.dao.GenericDaoImpl;
import points.strategy.dao.StrategyDao;
import points.strategy.dto.Strategy;

import javax.ejb.Stateless;

/**
 * Created by aardelean on 19.10.2014.
 */
@Stateless
public class StrategyDaoImpl extends GenericDaoImpl<Strategy> implements StrategyDao{

}
