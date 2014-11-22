package points.message.dto;

import points.user.dto.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aardelean on 14.09.2014.
 */
@MappedSuperclass
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Date time;
    private String location;

    @ManyToOne
    private User issuer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getIssuer() {
        return issuer;
    }

    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }
}
