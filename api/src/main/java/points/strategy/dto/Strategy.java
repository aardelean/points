package points.strategy.dto;

import points.group.dto.Group;
import points.user.dto.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by aardelean on 14.09.2014.
 */
@Entity
@Table(name="strategy")
public class Strategy  implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private StrategyType strategyType;

    private boolean enabled;


    @OneToMany(mappedBy = "strategy")
    private List<Group> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
