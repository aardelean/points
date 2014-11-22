package points.group.dto;

import points.user.dto.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by aardelean on 14.09.2014.
 */
@Entity
@Table(name="userStatus")
public class UserStatus {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "status")
    private List<Group> groups;

    @Enumerated(EnumType.STRING)
    private UserStatusType type;

    private Integer pingTime;

    private Date lastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public UserStatusType getType() {
        return type;
    }

    public void setType(UserStatusType type) {
        this.type = type;
    }

    public Integer getPingTime() {
        return pingTime;
    }

    public void setPingTime(Integer pingTime) {
        this.pingTime = pingTime;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
