package points.user;

import java.util.List;

import javax.ejb.Local;

import points.GenericFacade;
import points.group.dto.UserStatus;
import points.transport.user.UserStatusTransport;
import points.transport.user.UserTransport;
import points.user.dto.User;

/**
 * Created by aardelean on 13.12.2014.
 */
@Local
public interface UserFacade extends GenericFacade<UserTransport, User>{

	UserTransport getUser(Long id);

    UserTransport createUser(UserTransport user);

    UserTransport updateUser(UserTransport user);

    void deleteUser(UserTransport user);

    UserTransport addFriends(UserTransport user, List<UserTransport> friends);

    UserTransport convertUserTransport(User user);

    UserStatusTransport convertUserStatusTransport(UserStatus userStatus);

}
