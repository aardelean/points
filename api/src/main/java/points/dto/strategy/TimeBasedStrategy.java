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
}
