package points.dto.user;

import points.dao.user.dto.User;
import points.dto.social.SocialProvider;

import javax.persistence.*;

/**
 * Created by aardelean on 14.09.2014.
 */
@Entity
@Table(name="friend")
public class Friend {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Enumerated(EnumType.STRING)
    private SocialProvider provider;
    private String friendsIds;

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

    public SocialProvider getProvider() {
        return provider;
    }

    public void setProvider(SocialProvider provider) {
        this.provider = provider;
    }

    public String getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(String friendsIds) {
        this.friendsIds = friendsIds;
    }
}
