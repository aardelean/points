package points.strategy.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 28.09.2014.
 */

@Entity
@Table(name="timestrategy")
@DiscriminatorValue(value = "TIME")
public class TimeBasedStrategy  extends Strategy {
    @Enumerated(EnumType.STRING)
    private TimeStrategyType timeStrategyType;
    private Date startTime;
    private Date endTime;

    public TimeStrategyType getTimeStrategyType() {
        return timeStrategyType;
    }

    public void setTimeStrategyType(TimeStrategyType timeStrategyType) {
        this.timeStrategyType = timeStrategyType;
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
