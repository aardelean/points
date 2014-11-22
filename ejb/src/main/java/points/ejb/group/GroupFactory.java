package points.ejb.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import points.group.dto.Group;
import points.group.dto.UserStatus;
import points.group.dto.UserStatusType;
import points.user.dto.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.List;
import java.util.Set;

/**
 * Created by aardelean on 04.10.2014.
 */
@Singleton
public class GroupFactory {


    private ObjectWriter writer;

    private ObjectMapper objectMapper;

    private CollectionType type;

    static final Boolean defaultEnabled = Boolean.TRUE;

    static final String defaultName = "friends";

    static final UserStatusType defaultType = UserStatusType.AVAILABLE;

    static final Integer defaultPingTime=30;

    @PostConstruct
    public void setup() {
        objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        typeFactory.constructCollectionType(Set.class, Long.class);
        type = typeFactory.constructCollectionType(Set.class, Long.class);
        writer = objectMapper.writer();
    }

    public UserStatus createUserStatus(User user, UserStatusType userStatusType){
        return createUserStatus(user, userStatusType, defaultPingTime);
    }

    public UserStatus createUserStatus(User user, UserStatusType userStatusType, Integer pingTime){
        UserStatus userStatus = new UserStatus();
        userStatus.setUser(user);
        userStatus.setPingTime(pingTime);
        userStatus.setType(userStatusType);
        return userStatus;
    }


    public UserStatus createUserStatus(User user){
        return createUserStatus(user, defaultType);
    }

    public Group createGroup(User creator,List<Long> contactsIds){
       Group group = createSimpleGroup(creator, contactsIds);
        group.setName(defaultName);
        return group;
    }


    public Group createGroup(User creator,String name, List<Long> contactsIds){
        Group group = createSimpleGroup(creator, contactsIds);
        group.setName(name);
        return group;
    }

    private Group createSimpleGroup(User creator, List<Long> contactsIds){
        Group group = new Group();
        group.setCreator(creator);
        group.setEnabled(defaultEnabled);
        group.setStatus(createUserStatus(creator));
        try {
            group.setContactIds(writer.writeValueAsString(contactsIds));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("could not serialize contactsIds", e);
        }
        return group;
    }
}
