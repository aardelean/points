package points.socialProvider.configuration;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.oauth.application.OAuthAppSettingsBuilder;
import org.agorava.api.oauth.application.OAuthApplication;
import org.agorava.api.oauth.application.Param;
import org.agorava.facebook.Facebook;

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
            @Param(name = OAuthAppSettingsBuilder.CALLBACK, value = "https://localhost:8443/socialProvider/facebook/callback")})
    public static OAuthAppSettings facebookSettings;
}
