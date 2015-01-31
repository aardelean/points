package points.strategy.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by aardelean on 28.09.2014.
 */
@Entity
@Table(name="locationStrategy")
@DiscriminatorValue(value = "LOCATION")
public class LocationBasedStrategy extends Strategy{
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    private String locationName;

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
