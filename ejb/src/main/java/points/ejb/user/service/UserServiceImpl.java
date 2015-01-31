package points.ejb.user.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import points.user.UserService;
import points.user.dao.FriendDao;
import points.user.dao.UserDao;
import points.user.dto.ContactCollection;
import points.user.dto.SocialProvider;
import points.user.dto.User;

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
    public User createUser(String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo) {
        Date date = new Date();
        User user = new UserBuilder().firstName(firstname).lastName(lastname).username(username).password(password)
                .socialProvider(socialProvider).email(email).phoneNo(phoneNo).creationDate(date).lastModifiedDate(date).build();
        userDao.save(user);
        return user;
    }

    @Override
    public User updateUser(Long userId, String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo) {
        User user = userDao.findById(userId);
        Date date = new Date();
        user = new UserBuilder(user).firstName(firstname).lastName(lastname).username(username).password(password)
                .socialProvider(socialProvider).email(email).phoneNo(phoneNo).lastModifiedDate(date).build();
        userDao.update(user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userDao.findById(userId);
        userDao.delete(user);
    }

    @Override
    public User addFriends(Long userId, String socialProvider, String serializedIds) {
        SocialProvider socialProviderEnum = SocialProvider.valueOf(socialProvider);
        ContactCollection contactCollection = friendDao.findByUserIdAndSocialProvider(userId, socialProviderEnum);
        User user = null;
        if(contactCollection ==null){
            user = userDao.findById(userId);
            contactCollection = new FriendBuilder().friendsIds(serializedIds).provider(socialProviderEnum).user(user).build();
            friendDao.save(contactCollection);
        }else{
            contactCollection.setContactIds(serializedIds.getBytes());
            friendDao.update(contactCollection);
            user = contactCollection.getUser();
        }
        return user;
    }

    @Override
    public List<User> getUsersWithIds(List<Long> ids) {
        return userDao.getUsersWithIds(ids);
    }

	@Override
	public User getUser(Long id) {
		return userDao.findById(id);
	}
}
