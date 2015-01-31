package points.transport.user;

import points.transport.AbstractTransportEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aardelean on 13.12.2014.
 */
public class UserDetailsTransport extends AbstractTransportEntity implements Serializable{

    private String username;
    private String phoneNo;
    private Date creationDate;
    private Date lastModifiedDate;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
