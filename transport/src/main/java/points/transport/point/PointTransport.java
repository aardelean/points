package points.transport.point;

import points.transport.AbstractTransportEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 13.12.2014.
 */
public class PointTransport extends AbstractTransportEntity implements Serializable{
    private String longitude;
    private String latitude;
    private Date time;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
