package points.keycloak.social.facebook;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.keycloak.broker.oidc.AbstractOAuth2IdentityProvider;
import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.broker.oidc.util.JsonSimpleHttp;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.broker.provider.IdentityBrokerException;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.social.SocialIdentityProvider;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.json.Json;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class FacebookIdentityProvider extends AbstractOAuth2IdentityProvider implements SocialIdentityProvider {

    public static final String AUTH_URL = "https://graph.facebook.com/oauth/authorize";
    public static final String TOKEN_URL = "https://graph.facebook.com/oauth/access_token";
    public static final String PROFILE_URL = "https://graph.facebook.com/me";
    public static final String DEFAULT_SCOPE = "email";

    private ConnectionFactory connectionFactory;
    private Destination queue;

    public FacebookIdentityProvider(OAuth2IdentityProviderConfig config, ConnectionFactory connectionFactory, Destination queue) {
        super(config);
        config.setAuthorizationUrl(AUTH_URL);
        config.setTokenUrl(TOKEN_URL);
        config.setUserInfoUrl(PROFILE_URL);
        this.connectionFactory=connectionFactory;
        this.queue = queue;
    }

    protected BrokeredIdentityContext doGetFederatedIdentity(String accessToken) {
        try {
            JsonNode profile = JsonSimpleHttp.asJson(SimpleHttp.doGet(PROFILE_URL).header("Authorization", "Bearer " + accessToken));
            String id = getJsonProperty(profile, "id");

            BrokeredIdentityContext user = new BrokeredIdentityContext(id);

            String email = getJsonProperty(profile, "email");

            user.setEmail(email);

            String username = getJsonProperty(profile, "username");

            if (username == null) {
                if (email != null) {
                    username = email;
                } else {
                    username = id;
                }
            }

            user.setUsername(username);

            String firstName = getJsonProperty(profile, "first_name");
            String lastName = getJsonProperty(profile, "last_name");

            if (lastName == null) {
                lastName = "";
            } else {
                lastName = " " + lastName;
            }

            user.setName(firstName + lastName);
            user.setIdpConfig(getConfig());
            user.setIdp(this);

            // Sends a text message to the queue
            try(JMSContext context = connectionFactory.createContext()) {
                ObjectMessage message = context.createObjectMessage(buildMessage(user, accessToken));
                context.createProducer().send(queue, message);
            }
//            JsonNode friends = JsonSimpleHttp.asJson(SimpleHttp.doGet(FRIENDS).header("Authorization", "Bearer " + accessToken));
            return user;
        } catch (Exception e) {
            throw new IdentityBrokerException("Could not obtain user profile from facebook.", e);
        }
    }

    private UserSignupMessage buildMessage(BrokeredIdentityContext context, String accessToken) throws IOException {
        UserSignupMessage result=new UserSignupMessage();
        result.setAuthToken(accessToken);
        result.setEmail(context.getEmail());
        result.setFirstName(context.getFirstName());
        result.setLastName(context.getLastName());
        result.setProvider("FACEBOOK");
        return result;
    }

    @Override
    protected String getDefaultScopes() {
        return DEFAULT_SCOPE;
    }
}
