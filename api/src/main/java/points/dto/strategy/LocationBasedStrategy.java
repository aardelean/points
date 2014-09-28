package points.dto.strategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by aardelean on 28.09.2014.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="locationStrategy")
@IdClass(Strategy.class)
@DiscriminatorColumn(name = "strategyType")
@DiscriminatorValue(value = "LOCATION")
public class LocationBasedStrategy implements Serializable{
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    private String locationName;
    @OneToOne
    @Id
    @JoinColumn(name = "strategyId")
    private Strategy strategy;


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

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
