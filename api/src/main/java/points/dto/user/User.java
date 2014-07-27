package points.dto.user;

import org.hibernate.annotations.GenericGenerator;
import points.dto.social.SocialProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 7/13/2014.
 */

@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String username;

    private String password;

    private String socialIdentifier;

    private SocialProvider socialProvider;

    //@OneToMany(fetch = FetchType.LAZY)
    @Transient
    private List<User> friends;
    @Transient
    private VisibilityStatus visibilityStatus;
    @Transient
    private Date lastSocialUpdate;
    @Transient
    private Date lastUpdatedDate;
    @Transient
    private Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSocialIdentifier() {
        return socialIdentifier;
    }

    public void setSocialIdentifier(String socialIdentifier) {
        this.socialIdentifier = socialIdentifier;
    }

    public SocialProvider getSocialProvider() {
        return socialProvider;
    }

    public void setSocialProvider(SocialProvider socialProvider) {
        this.socialProvider = socialProvider;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public VisibilityStatus getVisibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(VisibilityStatus visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
    }

    public Date getLastSocialUpdate() {
        return lastSocialUpdate;
    }

    public void setLastSocialUpdate(Date lastSocialUpdate) {
        this.lastSocialUpdate = lastSocialUpdate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
