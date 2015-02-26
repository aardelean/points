package points.group;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

import points.AbstractEEDeployment;
import points.group.dto.Group;
import points.group.dto.UserStatus;
import points.group.dto.UserStatusType;

/**
 * Created by aardelean on 30.09.2014.
 */
public class GroupServiceTest extends AbstractEEDeployment{
    @Inject
    private GroupService classUnderTest;

    @PersistenceContext(unitName = "persist")
    private EntityManager em;

    private static final Long CREATOR_ID=100L;
    private static final Long GROUP_ID=100L;
    private static final Long USERSTATUS_ID=100L;


    @Test
    public void test(){
        Assert.assertNotNull(classUnderTest);
    }

    @Test
    public void addGroup() throws ClassNotFoundException, SQLException, InstantiationException, IOException, IllegalAccessException {
        List<Long> friendIds = new ArrayList<>();
        friendIds.add(9l);
        friendIds.add(11l);
        classUnderTest.addGroup(friendIds,CREATOR_ID,"defaultName", true, UserStatusType.AVAILABLE.name(),300);
        Query query = em.createQuery("FROM Group order by id desc").setMaxResults(1);
        Group group = (Group)query.getSingleResult();
        Assert.assertTrue(group.getEnabled());
        Assert.assertEquals("defaultName", group.getName());
        Assert.assertNotNull(group.getContacts());
    }

    @Test
    public void changeStatus() throws ClassNotFoundException, SQLException, InstantiationException, IOException, IllegalAccessException {
        classUnderTest.changeStatus(CREATOR_ID,UserStatusType.INVISIBLE, GROUP_ID);
        UserStatus userstatus = em.find(UserStatus.class, USERSTATUS_ID);
        Assert.assertEquals(UserStatusType.INVISIBLE, userstatus.getType());
    }

    @Test
    public void addUserStatus(){
        classUnderTest.addUserStatus(CREATOR_ID, UserStatusType.CAREFULLY_WATCHED);
        Query query = em.createQuery("FROM UserStatus order by id desc").setMaxResults(1);
        UserStatus userStatus = (UserStatus)query.getSingleResult();
        Assert.assertEquals(CREATOR_ID, userStatus.getUser().getId());
        Assert.assertEquals(UserStatusType.CAREFULLY_WATCHED, userStatus.getType());

    }

}
