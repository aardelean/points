package points.strategy;

import points.GenericFacade;
import points.transport.strategy.StrategyTransport;

/**
 * Created by aardelean on 14.12.2014.
 */
public interface StrategyFacade<T, E> extends GenericFacade<T,E> {

    StrategyTransport disableStrategy(StrategyTransport strategy);

    StrategyTransport enableStrategy(StrategyTransport strategy);

    StrategyTransport addGroups(StrategyTransport strategy);

    StrategyTransport removeGroups(StrategyTransport strategy);

    StrategyTransport updateStrategy(StrategyTransport strategy);

    void removeStrategy(StrategyTransport strategy);

    StrategyTransport addLocationStrategy(StrategyTransport strategyTransport);

    StrategyTransport addTimeStrategy(StrategyTransport strategyTransport);
}
