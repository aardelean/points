package points.dto.user;

import org.hibernate.annotations.GenericGenerator;
import points.dto.social.SocialProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 7/13/2014.
 */

@Entity
@Table(name="contact")
public class Contact implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    infinispan config
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer id;
    private String username;
    private String password;
    private String phoneNo;
    @OneToMany(mappedBy = "contact")
    private List<VisibilityGroup> visibilityGroups;
    private Date lastUpdatedDate;
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<VisibilityGroup> getVisibilityGroups() {
        return visibilityGroups;
    }

    public void setVisibilityGroups(List<VisibilityGroup> visibilityGroups) {
        this.visibilityGroups = visibilityGroups;
    }
}
