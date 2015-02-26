package points.transport.strategy;

import points.transport.AbstractTransportEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 13.12.2014.
 */
public class TimeStrategyTransport extends AbstractTransportEntity implements Serializable {
    private Date startDate;
    private Date endDate;
    private String type;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
