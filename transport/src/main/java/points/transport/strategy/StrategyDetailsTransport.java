package points.transport.strategy;

import points.transport.group.GroupTransport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aardelean on 28.12.2014.
 */
public class StrategyDetailsTransport extends StrategyTransport implements Serializable{

    private List<GroupTransport> groups;

    public List<GroupTransport> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupTransport> groups) {
        this.groups = groups;
    }
}
