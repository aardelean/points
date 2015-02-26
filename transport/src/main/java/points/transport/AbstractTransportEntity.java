package points.transport;

/**
 * Created by aardelean on 14.12.2014.
 */
public class AbstractTransportEntity {

    private Long id;

    private Long currentUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }
}
