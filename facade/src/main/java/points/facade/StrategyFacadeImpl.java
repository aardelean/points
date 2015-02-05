package points.facade;

import javax.ejb.Stateless;

import points.strategy.StrategyFacade;
import points.strategy.dto.Strategy;
import points.transport.strategy.StrategyTransport;

/**
 * Created by aardelean on 28.12.2014.
 */
@Stateless
public class StrategyFacadeImpl implements StrategyFacade<StrategyTransport, Strategy>{
    @Override
    public StrategyTransport disableStrategy(StrategyTransport strategy) {
        return null;
    }

    @Override
    public StrategyTransport enableStrategy(StrategyTransport strategy) {
        return null;
    }

    @Override
    public StrategyTransport addGroups(StrategyTransport strategy) {
        return null;
    }

    @Override
    public StrategyTransport removeGroups(StrategyTransport strategy) {
        return null;
    }

    @Override
    public StrategyTransport updateStrategy(StrategyTransport strategy) {
        return null;
    }

    @Override
    public void removeStrategy(StrategyTransport strategy) {
    }

    @Override
    public StrategyTransport addLocationStrategy(StrategyTransport strategyTransport) {
        return null;
    }

    @Override
    public StrategyTransport addTimeStrategy(StrategyTransport strategyTransport) {
        return null;
    }

    @Override
    public StrategyTransport convert(Strategy strategy) {
        return null;
    }
}
