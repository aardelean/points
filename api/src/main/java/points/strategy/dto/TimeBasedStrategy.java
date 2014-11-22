package points.strategy.dto;

import points.user.dto.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 28.09.2014.
 */

@Entity
@Table(name="timestrategy")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@IdClass(Strategy.class)
@DiscriminatorColumn(name = "strategyType")
@DiscriminatorValue(value = "TIME")
public class TimeBasedStrategy  implements Serializable {
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private TimeStrategyType timeStrategyType;
    private Date startTime;
    private Date endTime;
    @OneToOne
    @Id
    @JoinColumn(name = "strategyId")
    private Strategy strategy;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean fromNowOn) {
        this.enabled = fromNowOn;
    }


    public TimeStrategyType getTimeStrategyType() {
        return timeStrategyType;
    }

    public void setTimeStrategyType(TimeStrategyType timeStrategyType) {
        this.timeStrategyType = timeStrategyType;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeBasedStrategy that = (TimeBasedStrategy) o;

        if (strategy != null ? !strategy.equals(that.strategy) : that.strategy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return strategy != null ? strategy.hashCode() : 0;
    }
}
