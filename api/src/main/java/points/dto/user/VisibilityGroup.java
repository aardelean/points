package points.dto.user;

import javax.persistence.*;

/**
 * Created by aardelean on 15.08.2014.
 */
@Entity
@Table(name = "visibilityGroup")
public class VisibilityGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private VisibilityStatus status;

    @Column(columnDefinition = "TEXT")
    private String serializedUserIds;

    @ManyToOne
    @JoinColumn(name = "contactId")
    private Contact contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VisibilityStatus getStatus() {
        return status;
    }

    public void setStatus(VisibilityStatus status) {
        this.status = status;
    }

    public String getSerializedUserIds() {
        return serializedUserIds;
    }

    public void setSerializedUserIds(String serializedUserIds) {
        this.serializedUserIds = serializedUserIds;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
