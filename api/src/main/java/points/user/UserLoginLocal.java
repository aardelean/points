package points.user;

import javax.ejb.Local;

/**
 * Created by alex on 7/12/2014.
 */
@Local
public interface UserLoginLocal {
    public String login(String username, String password);
}
