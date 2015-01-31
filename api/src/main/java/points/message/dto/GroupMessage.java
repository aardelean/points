package points.message.dto;

import points.group.dto.Group;

import javax.persistence.*;

/**
 * Created by aardelean on 28.09.2014.
 */
@Entity
@Table(name = "groupmessage")
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
