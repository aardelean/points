package points.message.dto;

import points.user.dto.User;

import javax.persistence.*;

/**
 * Created by aardelean on 28.09.2014.
 */
@Entity
@DiscriminatorColumn(name = "type")
@Table(name = "usermessage")
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
