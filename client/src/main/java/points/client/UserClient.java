package points.client;

import points.user.UserLogin;
import points.user.dao.UserDao;
import points.user.dto.User;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by alex on 7/12/2014.
 */
@Named
@ConversationScoped
public class UserClient implements Serializable{



    @EJB
    private UserLogin userLoginLocal;

    @EJB
    private UserDao userDaoLocal;

    private String username;
    private String password;

    private Long id;

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
        User contactById = userDaoLocal.findById(id);
        return "nice to meet you, "+ contactById.getUsername() +" with id = "+id;
    }

    @Transactional
    public String saveUser(){
        id = userLoginLocal.login(username, password);
        return "welcome";
    }



}
