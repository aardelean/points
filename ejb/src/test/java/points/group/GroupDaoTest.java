package points.group;

import org.junit.Assert;
import org.junit.Test;
import points.AbstractEEDeployment;
import points.dao.group.dao.GroupDao;
import points.dao.group.dto.Group;

import javax.inject.Inject;

/**
 * Created by aardelean on 30.09.2014.
 */
public class GroupDaoTest extends AbstractEEDeployment{
    @Inject
    private GroupDao classUnderTest;


    @Test
    public void test(){
        Assert.assertNotNull(classUnderTest);
    }

    @Test
    public void testCache(){
        Group group = new Group();
        group.setContactIds("contactsIds");
        group.setName("groupname");
//        group.setCreator();
//        classUnderTest.save();
    }

}
