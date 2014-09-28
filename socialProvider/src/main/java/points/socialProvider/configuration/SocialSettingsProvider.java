package points.socialProvider.configuration;

import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.oauth.application.OAuthAppSettingsBuilder;
import org.agorava.api.oauth.application.OAuthApplication;
import org.agorava.api.oauth.application.Param;
import org.agorava.facebook.Facebook;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Created by aardelean on 17.08.2014.
 */

@Named
@Singleton
public class SocialSettingsProvider {

    @ApplicationScoped
    @Produces
    @Facebook
    @OAuthApplication(params = {@Param(name = OAuthAppSettingsBuilder.PREFIX, value = "facebook"),
            @Param(name = OAuthAppSettingsBuilder.CALLBACK, value = "http://localhost:8080/socialProvider/facebook/callback")})
    public static OAuthAppSettings facebookSettings;
}
