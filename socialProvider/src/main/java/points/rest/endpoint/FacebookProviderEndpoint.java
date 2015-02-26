package points.rest.endpoint;


import org.agorava.api.service.OAuthLifeCycleService;
import org.agorava.facebook.Facebook;
import org.agorava.facebook.GraphApi;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by aardelean on 15.08.2014.
 */
@Path("/facebook")
@Named
public class FacebookProviderEndpoint {

    @Inject
    OAuthLifeCycleService lifeCycleService;

    @Inject
    @Facebook
    private GraphApi graphApi;

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testRest(){
        return "success";
    }

    @GET
    @Path("/connect")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connect() throws URISyntaxException {
        lifeCycleService.buildSessionFor("Facebook");
        return Response.seeOther(new URI(lifeCycleService.startDanceFor("Facebook"))).build();
    }

    @GET
    @Path("/callback")
    @Produces(MediaType.APPLICATION_JSON)
    public String callback(@QueryParam("code") String code){
        lifeCycleService.endDance(code);
        return graphApi.fetchObject("me/friends",String.class);
    }
}
