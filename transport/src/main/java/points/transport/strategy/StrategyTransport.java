package points.transport.strategy;

import points.transport.AbstractTransportEntity;
import points.transport.user.UserStatusTransport;
import points.transport.group.GroupTransport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aardelean on 13.12.2014.
 */
public class StrategyTransport extends AbstractTransportEntity implements Serializable {
    private Long id;
    private TimeStrategyTransport timeStrategy;
    private LocationStrategyTransport locationStrategy;
    private String name;
    private UserStatusTransport userStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeStrategyTransport getTimeStrategy() {
        return timeStrategy;
    }

    public void setTimeStrategy(TimeStrategyTransport timeStrategy) {
        this.timeStrategy = timeStrategy;
    }

    public LocationStrategyTransport getLocationStrategy() {
        return locationStrategy;
    }

    public void setLocationStrategy(LocationStrategyTransport locationStrategy) {
        this.locationStrategy = locationStrategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatusTransport getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusTransport userStatus) {
        this.userStatus = userStatus;
    }


}
