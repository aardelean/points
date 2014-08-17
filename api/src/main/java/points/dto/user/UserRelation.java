package points.dto.user;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by aardelean on 15.08.2014.
 */
@Entity
@Table(name = "userRelation")
public class UserRelation {

    @EmbeddedId
    private RelationPK relation;

    @Column(columnDefinition = "TEXT")
    private String serializedUserIds;

    private String socialIdentifier;

    private Date lastUpdatedDate;

    public RelationPK getRelation() {
        return relation;
    }

    public void setRelation(RelationPK relation) {
        this.relation = relation;
    }

    public String getSerializedUserIds() {
        return serializedUserIds;
    }

    public void setSerializedUserIds(String serializedUserIds) {
        this.serializedUserIds = serializedUserIds;
    }

    public String getSocialIdentifier() {
        return socialIdentifier;
    }

    public void setSocialIdentifier(String socialIdentifier) {
        this.socialIdentifier = socialIdentifier;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
