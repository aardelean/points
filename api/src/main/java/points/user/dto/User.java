package points.user.dto;

import points.group.dto.Group;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by aardelean on 14.09.2014.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    private String socialId;

    @OneToMany(mappedBy = "user")
    private List<ContactCollection> contactCollections;

    @OneToMany(mappedBy = "creator")
    private List<Group> createdGroups;

    @ManyToMany(mappedBy = "contacts", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Group> belongingGroups;

    private String phoneNo;
    private String email;
    private Date creationDate;
    private Date lastModifiedDate;
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<ContactCollection> getContactCollections() {
        return contactCollections;
    }

    public List<Group> getCreatedGroups() {
        return createdGroups;
    }

    public void setCreatedGroups(List<Group> createdGroups) {
        this.createdGroups = createdGroups;
    }

    public void setContactCollections(List<ContactCollection> contactCollections) {
        this.contactCollections = contactCollections;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public SocialProvider getSocialProvider() {
        return socialProvider;
    }

    public void setSocialProvider(SocialProvider socialProvider) {
        this.socialProvider = socialProvider;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Group> getBelongingGroups() {
        return belongingGroups;
    }

    public void setBelongingGroups(List<Group> belongingGroups) {
        this.belongingGroups = belongingGroups;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }
}
