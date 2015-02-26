package points.user;

import java.util.List;
import javax.ejb.Local;
import points.user.dto.User;

/**
 * Created by aardelean on 19.10.2014.
 */
@Local
public interface UserService {

    User createUser(String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo);

    User updateUser(Long userId, String username, String lastname, String firstname, String password, String socialProvider, String email, String phoneNo);

    void deleteUser(Long userId);

    User addFriends(Long userId,String socialProvider, String serializedIds);

    List<User> getUsersWithIds(List<Long> ids);

	User getUser(Long id);
}
