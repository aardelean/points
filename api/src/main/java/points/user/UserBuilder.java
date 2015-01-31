package points.user;

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


    public UserBuilder username(String username){
        this.username = username;
        return this;
    }

    public UserBuilder lastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder firstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserBuilder phoneNo(String phoneNo){
        this.phoneNo = phoneNo;
        return this;
    }

    public UserBuilder email(String email){
        this.email = email;
        return this;
    }

    public UserBuilder password(String password){
        this.password = password;
        return this;
    }

    public UserBuilder creationDate(Date creationDate){
        this.creationDate = creationDate;
        return this;
    }

    public UserBuilder lastModifiedDate(Date lastModifiedDate){
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public UserBuilder socialProvider(String socialProvider){
        this.socialProvider = SocialProvider.valueOf(socialProvider);
        return this;
    }

    public User build(){
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
