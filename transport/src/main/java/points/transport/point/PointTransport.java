package points.transport.point;

import java.io.Serializable;
import java.util.Date;

import points.transport.AbstractTransportEntity;

/**
 * Created by aardelean on 13.12.2014.
 */
public class PointTransport extends AbstractTransportEntity implements Serializable{
    private Double longitude;
    private Double latitude;
    private Date time;
	private Long userId;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
