package points.strategy;

import org.junit.Test;
import points.AbstractEEDeployment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by aardelean on 22.11.2014.
 */
public class StrategyServiceTest extends AbstractEEDeployment{

    @Inject
    private StrategyService strategyService;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createDailyTimeStrategy(){
        strategyService.createDailyTimeStrategy(new Date(), new Date());
    }


}
