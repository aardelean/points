package points.dto.message;

import points.dao.user.dto.User;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by aardelean on 28.09.2014.
 */
@Entity
@DiscriminatorColumn(name = "type")
public class UserMessage extends Message{
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
