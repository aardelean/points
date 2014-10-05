package points.dao.user;

import javax.ejb.Local;

/**
 * Created by alex on 7/12/2014.
 */
@Local
public interface UserLogin {
    public Long login(String username, String password);
}
