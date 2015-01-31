package points.message;

import org.junit.Assert;
import org.junit.Test;
import points.AbstractEEDeployment;
import points.message.dto.GroupMessage;
import points.message.dto.UserMessage;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by aardelean on 22.11.2014.
 */
public class MessageServiceTest extends AbstractEEDeployment{

    private static Long SENDER_ID = 100l;

    private static Long RECEIVER_ID = 100l;

    @Inject
    private MessageService classUnderTest;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createAndSendUserMessage(){
        String content = "content";
        String location = "Munchen";
        classUnderTest.createAndSendUserMessage(SENDER_ID,RECEIVER_ID, content, location);
        Query query = em.createQuery("From UserMessage order by id desc").setMaxResults(1);
        UserMessage message = (UserMessage)query.getSingleResult();
        Assert.assertEquals(SENDER_ID, message.getIssuer().getId());
        Assert.assertEquals(RECEIVER_ID, message.getUser().getId());
        Assert.assertEquals( content, message.getContent());
        Assert.assertEquals( location, message.getLocation());
    }

    @Test
    public void createAndSendGroupMessage(){
        String content = "content";
        String location = "Munchen";
        classUnderTest.createAndSendGroupMessage(SENDER_ID,RECEIVER_ID, content, location);
        Query query = em.createQuery("From GroupMessage order by id desc").setMaxResults(1);
        GroupMessage message = (GroupMessage)query.getSingleResult();
        Assert.assertEquals(SENDER_ID, message.getIssuer().getId());
        Assert.assertEquals(RECEIVER_ID, message.getGroup().getId());
        Assert.assertEquals( content, message.getContent());
        Assert.assertEquals( location, message.getLocation());
    }
}
