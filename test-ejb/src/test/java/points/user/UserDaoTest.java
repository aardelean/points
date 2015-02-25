package points.user;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import points.AbstractEEDeployment;
import points.user.dao.UserDao;
import points.user.dto.User;

/**
 * Created by aardelean on 04.10.2014.
 */
public class UserDaoTest extends AbstractEEDeployment{

    @Inject
    private UserDao userDao;

    @Test
    public void testInsertFind(){
        User user = new User();
        user.setFirstName("firsname");
        user.setLastName("lastname");
        user.setEmail("email");
        user.setPassword("password");
        user.setUsername("username_test");
        userDao.save(user);

        User found = userDao.findUserByUserName("username_test");
        Assert.assertNotNull(found);
        Assert.assertEquals("email",user.getEmail());
    }
}
