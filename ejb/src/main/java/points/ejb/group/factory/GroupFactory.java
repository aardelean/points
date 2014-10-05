package points.ejb.group.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import points.dao.group.dto.Group;
import points.dao.group.dto.UserStatus;
import points.dao.group.dto.UserStatusType;
import points.dao.user.dto.User;

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

    private static final Boolean defaultEnabled = Boolean.TRUE;

    private static final String defaultName = "friends";

    private static final UserStatusType defaultType = UserStatusType.AVAILABLE;

    private static final Integer defaultPingTime=30;

    @PostConstruct
    public void setup() {
        objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        typeFactory.constructCollectionType(Set.class, Long.class);
        type = typeFactory.constructCollectionType(Set.class, Long.class);
        writer = objectMapper.writer();
    }

    public UserStatus createUserStatus(User user){
        UserStatus userStatus = new UserStatus();
        userStatus.setUser(user);
        userStatus.setPingTime(defaultPingTime);
        userStatus.setType(defaultType);
        return userStatus;
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
