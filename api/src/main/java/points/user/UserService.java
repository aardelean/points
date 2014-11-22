package points.user;

import javax.ejb.Local;

/**
 * Created by aardelean on 19.10.2014.
 */
@Local
public interface UserService {
    void createUser(String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo);

    void updateUser(Long userId, String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo);
    void deleteUser(Long userId);
    void addFriends(Long userId,String socialProvider, String serializedIds);

}
