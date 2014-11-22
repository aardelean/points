package points.ejb.user;

import points.user.dto.Friend;
import points.user.dto.SocialProvider;
import points.user.dto.User;

/**
 * Created by aardelean on 25.10.2014.
 */
public class FriendBuilder {
    private User user;
    private SocialProvider provider;
    private String friendsIds;
    private Friend friend;

    public FriendBuilder user(User user){
        this.user = user;
        return this;
    }
    public FriendBuilder provider(SocialProvider socialProvider){
        this.provider = socialProvider;
        return this;
    }
    public FriendBuilder friendsIds(String friendsIds){
        this.friendsIds = friendsIds;
        return this;
    }

    public Friend build(){
        friend = new Friend();

        friend.setUser(user);
        friend.setProvider(provider);
        friend.setFriendsIds(friendsIds);

        return friend;
    }
}
