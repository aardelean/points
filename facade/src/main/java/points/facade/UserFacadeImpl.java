package points.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import points.group.dto.UserStatus;
import points.transport.user.UserStatusTransport;
import points.transport.user.UserTransport;
import points.user.UserFacade;
import points.user.UserService;
import points.user.dto.User;

/**
 * Created by aardelean on 28.12.2014.
 */
@Stateless
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserFacadeImpl implements UserFacade{

	@EJB
	private UserService userService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public UserTransport getUser(@PathParam("id") Long id) {
		return convert(userService.getUser(id));
	}

	@Override
    public UserTransport createUser(UserTransport user) {
        return convert(userService.createUser(user.getUsername(),user.getLastName(), user.getFirstName(),user.getPassword(),user.getSocialProvider(),user.getEmail(),user.getPhoneNo()));
    }

    @Override
    public UserTransport updateUser(UserTransport user) {
        return null;
    }

    @Override
    public void deleteUser(UserTransport user) {

    }

    @Override
    public UserTransport addFriends(UserTransport user, List<UserTransport> friends) {
        return null;
    }

    @Override
    public UserTransport convertUserTransport(User user) {
        return null;
    }

    @Override
    public UserStatusTransport convertUserStatusTransport(UserStatus userStatus) {
        UserStatusTransport result = new UserStatusTransport();
        result.setPingTime(userStatus.getPingTime());
        result.setName(userStatus.getName());
        result.setStatus(userStatus.getType().name());
        return result;
    }

    @Override
    public UserTransport convert(User user) {
		UserTransport result = new UserTransport();
		result.setLastName(user.getLastName());
		result.setSocialProvider(user.getSocialProvider().name());
		result.setEmail(user.getEmail());
		result.setFirstName(user.getFirstName());
		result.setPhoneNo(user.getPhoneNo());
		result.setUsername(result.getUsername());
		result.setCurrentUserId(user.getId());
        return result;
    }

	private List<UserTransport> convertList(List<User> users){
		List<UserTransport> resultList = new ArrayList<>(users.size());
		for(User user : users){
			resultList.add(convert(user));
		}
		return resultList;
	}
}
