package users;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import points.dao.user.dao.UserDaoLocal;
import points.dto.user.User;
import points.ejb.user.dao.UserDao;

import javax.ejb.EJB;

/**
 * Created by aardelean on 27.07.2014.
 */
@RunWith(Arquillian.class)
public class ContactDaoTest {
    @EJB
    private UserDaoLocal userlDaoLocal;

    @Deployment
    public static EnterpriseArchive createTestArchive() {
        return ShrinkWrap.create(EnterpriseArchive.class, "test-points.ear")
                .addAsLibraries(createWebArchive());
            //    .addAsLibraries(createJavaArchive());
    }
    private static WebArchive createWebArchive(){
        return ShrinkWrap.create(WebArchive.class,"client.war");
    }

    private static JavaArchive createJavaArchive(){
        return ShrinkWrap.create(JavaArchive.class,"ejb.jar")
                .addClass(UserDaoLocal.class)
                .addClass(UserDao.class)
                .addAsResource("META-INF/ejb-jar.xml")
                .addAsResource("META-INF/MANIFEST.MF")
                .addAsResource("META-INF/persistence.xml");
    }

    @Test
    public void testInsertUser(){
        User contact =new User();
        contact.setUsername("test");
        contact.setPassword("pass");
        userlDaoLocal.saveUser(contact);
        Assert.assertNotNull(userlDaoLocal.findUserById(contact.getId()));
    }
}
