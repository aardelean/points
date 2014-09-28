package points.dto.strategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by aardelean on 14.09.2014.
 */
@Entity
@Table(name="strategy")
public class Strategy  implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private StrategyType strategyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }
}
