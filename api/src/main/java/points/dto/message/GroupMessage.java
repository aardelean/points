package points.dto.message;

import points.dto.user.Group;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by aardelean on 28.09.2014.
 */
@Entity
@DiscriminatorColumn(name = "type")
public class GroupMessage extends Message{
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
