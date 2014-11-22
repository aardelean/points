package points.ejb.user;

import points.user.UserService;
import points.user.dao.FriendDao;
import points.user.dao.UserDao;
import points.user.dto.Friend;
import points.user.dto.SocialProvider;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

/**
 * Created by aardelean on 25.10.2014.
 */
@Stateless
public class UserServiceImpl implements UserService{

    @EJB
    private UserDao userDao;

    @EJB
    private FriendDao friendDao;


    @Override
    public void createUser(String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo) {
        Date date = new Date();
        User user = new UserBuilder().firstName(firstname).lastName(lastname).username(username).password(password)
                .socialProvider(socialProvider).email(email).phoneNo(phoneNo).creationDate(date).lastModifiedDate(date).build();
        userDao.save(user);
    }

    @Override
    public void updateUser(Long userId, String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo) {
        User user = userDao.findById(userId);
        Date date = new Date();
        user = new UserBuilder(user).firstName(firstname).lastName(lastname).username(username).password(password)
                .socialProvider(socialProvider).email(email).phoneNo(phoneNo).lastModifiedDate(date).build();
        userDao.update(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userDao.findById(userId);
        userDao.delete(user);
    }

    @Override
    public void addFriends(Long userId, String socialProvider, String serializedIds) {
        SocialProvider socialProviderEnum = SocialProvider.valueOf(socialProvider);
        Friend friend = friendDao.findByUserIdAndSocialProvider(userId, socialProviderEnum);
        if(friend==null){
            User user = userDao.findById(userId);
            friend = new FriendBuilder().friendsIds(serializedIds).provider(socialProviderEnum).user(user).build();
            friendDao.save(friend);
        }else{
            friend.setFriendsIds(serializedIds);
            friendDao.update(friend);
        }
    }
}
