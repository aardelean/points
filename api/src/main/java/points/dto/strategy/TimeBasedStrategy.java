package points.dto.strategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 28.09.2014.
 */

@Entity
@Table(name="timeStrategy")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@IdClass(Strategy.class)
@DiscriminatorColumn(name = "strategyType")
@DiscriminatorValue(value = "TIME")
public class TimeBasedStrategy  implements Serializable {
    private boolean fromNowOn;
    private boolean daily;
    private boolean weekly;
    private boolean monthly;
    private Date startTime;
    private Date endTime;
    @OneToOne
    @Id
    @JoinColumn(name = "strategyId")
    private Strategy strategy;


    public boolean isFromNowOn() {
        return fromNowOn;
    }

    public void setFromNowOn(boolean fromNowOn) {
        this.fromNowOn = fromNowOn;
    }

    public boolean isDaily() {
        return daily;
    }

    public void setDaily(boolean daily) {
        this.daily = daily;
    }

    public boolean isWeekly() {
        return weekly;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
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

        if (daily != that.daily) return false;
        if (fromNowOn != that.fromNowOn) return false;
        if (monthly != that.monthly) return false;
        if (weekly != that.weekly) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (strategy != null ? !strategy.equals(that.strategy) : that.strategy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (fromNowOn ? 1 : 0);
        result = 31 * result + (daily ? 1 : 0);
        result = 31 * result + (weekly ? 1 : 0);
        result = 31 * result + (monthly ? 1 : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (strategy != null ? strategy.hashCode() : 0);
        return result;
    }
}
