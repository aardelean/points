package points.transport.group;

import points.transport.strategy.StrategyTransport;
import points.transport.user.UserTransport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aardelean on 28.12.2014.
 */
public class GroupDetailsTransport extends GroupTransport implements Serializable{

    private List<UserTransport> friends;

    private List<StrategyTransport> strategies;

    public List<UserTransport> getFriends() {
        return friends;
    }

    public void setFriends(List<UserTransport> friends) {
        this.friends = friends;
    }

    public List<StrategyTransport> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<StrategyTransport> strategies) {
        this.strategies = strategies;
    }
}
