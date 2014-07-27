package points.client;

import points.user.UserLoginLocal;
import points.user.dao.UserDaoLocal;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by alex on 7/12/2014.
 */
@Named
@ConversationScoped
public class UserClient implements Serializable{

    @EJB
    private UserLoginLocal userLoginLocal;

    @EJB
    private UserDaoLocal userDaoLocal;

    private String username;
    private String password;

    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage(){
        return "nice to meet you, "+userDaoLocal.findUserById(id);
    }

    public String saveUser(){
        id = userLoginLocal.login(username, password);
        return "welcome";
    }

}
