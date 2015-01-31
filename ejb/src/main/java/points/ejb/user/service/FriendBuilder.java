package points.ejb.user.service;

import points.user.dto.ContactCollection;
import points.user.dto.SocialProvider;
import points.user.dto.User;

/**
 * Created by aardelean on 25.10.2014.
 */
public class FriendBuilder {
    private User user;
    private SocialProvider provider;
    private String friendsIds;
    private ContactCollection contactCollection;

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

    public ContactCollection build(){
        contactCollection = new ContactCollection();

        contactCollection.setUser(user);
        contactCollection.setSource(provider);
        contactCollection.setContactIds(friendsIds.getBytes());

        return contactCollection;
    }
}
