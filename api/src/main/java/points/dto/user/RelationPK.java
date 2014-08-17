package points.dto.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by aardelean on 15.08.2014.
 */
@Embeddable
public class RelationPK implements Serializable{

    @Column
    @Enumerated(EnumType.STRING)
    private RelationProvider provider;

    @Column
    private Integer contactId;

    public RelationProvider getProvider() {
        return provider;
    }

    public void setProvider(RelationProvider provider) {
        this.provider = provider;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer userId) {
        this.contactId = userId;
    }
}
