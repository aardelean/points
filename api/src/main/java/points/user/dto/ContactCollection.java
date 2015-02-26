package points.user.dto;

import javax.persistence.*;

/**
 * Created by aardelean on 23.12.2014.
 */
@Entity
@Table(name="contactCollection")
public class ContactCollection {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private User user;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] contactIds;

    @Enumerated(EnumType.STRING)
    private SocialProvider source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContactIds() {
        return contactIds;
    }

    public void setContactIds(byte[] contactIds) {
        this.contactIds = contactIds;
    }

    public SocialProvider getSource() {
        return source;
    }

    public void setSource(SocialProvider source) {
        this.source = source;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
