package points.user.dao;

import points.GenericDao;
import points.user.dto.ContactCollection;
import points.user.dto.SocialProvider;

/**
 * Created by aardelean on 25.10.2014.
 */
public interface FriendDao extends GenericDao<ContactCollection> {

    ContactCollection findByUserIdAndSocialProvider(Long userId, SocialProvider socialProvider);
}
