package points.transport.strategy;

import points.transport.AbstractTransportEntity;

import java.io.Serializable;

/**
 * Created by aardelean on 13.12.2014.
 */
public class LocationStrategyTransport extends AbstractTransportEntity implements Serializable {

    private String type;

    private String location;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
