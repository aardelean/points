package points.dao.group.dto;

import points.dao.user.dto.User;

import javax.persistence.*;

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
    @Lob
    private String contactIds;

    @OneToOne
    @JoinColumn(name = "statusId")
    private UserStatus status;

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

    public String getContactIds() {
        return contactIds;
    }

    public void setContactIds(String contactIds) {
        this.contactIds = contactIds;
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
}
