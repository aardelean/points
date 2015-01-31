package points.strategy.dto;

import points.group.dto.Group;
import points.group.dto.UserStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Created by aardelean on 24.12.2014.
 */
@Entity
@Table(name="strategy")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "strategyType", discriminatorType=DiscriminatorType.STRING)
public class Strategy {
    @Id
    @GeneratedValue
    private Long id;

    private boolean enabled;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="status_id")
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "strategies", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Group> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
