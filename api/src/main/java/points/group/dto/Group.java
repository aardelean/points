package points.group.dto;

import points.strategy.dto.Strategy;
import points.user.dto.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by aardelean on 14.09.2014.
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private UserStatus status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "group_user", joinColumns = {
            @JoinColumn(name = "group_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id",
                    nullable = false, updatable = false) })
    private List<User> contacts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "group_strategy", joinColumns = {
            @JoinColumn(name = "group_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "strategy_id",
                    nullable = false, updatable = false) })
    private List<Strategy> strategies;

    private String name;
    private Boolean enabled = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }
}
