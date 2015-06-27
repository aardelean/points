package points.ejb.user;

import org.codehaus.jackson.JsonNode;
import org.jboss.logging.Logger;
import org.keycloak.broker.oidc.util.JsonSimpleHttp;
import org.keycloak.broker.provider.util.SimpleHttp;
import points.user.*;
import points.user.dao.UserDao;
import points.user.dto.SocialProvider;
import points.user.dto.User;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 7/12/2014.
 */


@MessageDriven(mappedName="java:/jms/queue/UsersLogin", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/UsersLogin"),
        @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/LocalFactory"),
})
public class UserLoginListener implements MessageListener {

    public static final String FRIENDS = "https://graph.facebook.com/me/friends";
    private static final Logger logger = Logger.getLogger(UserLoginListener.class);

    @EJB
    private UserDao dao;


    @Override
    @Transactional
    public void onMessage(Message message) {
        logger.info("RECEIVED NEW USER LOGIN MESSAGE!");
        if(message instanceof ObjectMessage){
            try {
                UserSignupMessage userSignupMessage = (UserSignupMessage)((ObjectMessage) message).getObject();
                User user = convertToUser(userSignupMessage);
                dao.save(user);
                JsonNode friends = JsonSimpleHttp.asJson(SimpleHttp.doGet(FRIENDS).header("Authorization", "Bearer " + userSignupMessage.getAuthToken()));
                List<JsonNode> friendIds = friends.findValues("id");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private User convertToUser(UserSignupMessage userSignupMessage){
        User result = new User();
        result.setEmail(userSignupMessage.getEmail());
        result.setFirstName(userSignupMessage.getFirstName());
        result.setLastName(userSignupMessage.getLastName());
        result.setSocialProvider(SocialProvider.valueOf(userSignupMessage.getProvider()));
        result.setSocialId(userSignupMessage.getSocialId());
        return result;
    }
}
