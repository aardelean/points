package points.ejb.user.service;

import points.user.dto.SocialProvider;
import points.user.dto.User;

import java.util.Date;

/**
 * Created by aardelean on 19.10.2014.
 */
public class UserBuilder {
    private String username;
    private String lastName;
    private String firstName;
    private SocialProvider socialProvider;
    private String phoneNo;
    private String email;
    private Date creationDate;
    private Date lastModifiedDate;
    private String password;

    private User user;

    private boolean update = false;

    public UserBuilder(){

    }

    public UserBuilder(User user){
        this.user = user;
        update = true;
    }


    public UserBuilder username(String username){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.username = username;
        return this;
    }

    public UserBuilder lastName(String lastName){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.lastName = lastName;
        return this;
    }

    public UserBuilder firstName(String firstName){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.firstName = firstName;
        return this;
    }

    public UserBuilder phoneNo(String phoneNo){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.phoneNo = phoneNo;
        return this;
    }

    public UserBuilder email(String email){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.email = email;
        return this;
    }

    public UserBuilder password(String password){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.password = password;
        return this;
    }

    public UserBuilder creationDate(Date creationDate){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.creationDate = creationDate;
        return this;
    }

    public UserBuilder lastModifiedDate(Date lastModifiedDate){
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public UserBuilder socialProvider(String socialProvider){
        this.socialProvider = SocialProvider.valueOf(socialProvider);
        if(update && user!=null){
            if(!user.getUsername().equals(username)){
                user.setUsername(username);
            }
        }
        return this;
    }

    public User build(){
        if(update && user!=null){
            return user;
        }else {
            user = new User();
            user.setCreationDate(creationDate);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastModifiedDate(lastModifiedDate);
            user.setPassword(password);
            user.setPhoneNo(phoneNo);
            user.setSocialProvider(socialProvider);
            user.setLastName(lastName);
            user.setUsername(username);
            return user;
        }
    }

}
