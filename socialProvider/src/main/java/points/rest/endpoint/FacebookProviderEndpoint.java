package points.rest.endpoint;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by aardelean on 15.08.2014.
 */
@Path("/facebook")
public class FacebookProviderEndpoint {

    private final static String APP_ID="625836187535333";
    private final static String APP_SECRET="c5460f0e605e483ce79adffc1bb8aa78";

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testRest(){
        return "success";
    }

    @GET
    @Path("/connect")
    @Produces(MediaType.APPLICATION_JSON)
    public String connect(){
//        FacebookExternalNetwork externalNetwork = new FacebookExternalNetwork(APP_ID,APP_SECRET);
//        return externalNetwork.connect("http://localhost:8080/socialProvider/facebook/test");
        return "wrong";
    }
}
