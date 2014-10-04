package points.dto.user;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "status")
    @JoinColumn(name = "groupId")
    private Group group;

    @Enumerated(EnumType.STRING)
    private UserStatusType type;

    private Integer pingTime;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
}
