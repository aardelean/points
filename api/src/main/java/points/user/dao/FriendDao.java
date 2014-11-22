package points.user.dao;

import points.GenericDao;
import points.user.dto.Friend;
import points.user.dto.SocialProvider;

/**
 * Created by aardelean on 25.10.2014.
 */
public interface FriendDao extends GenericDao<Friend> {

    Friend findByUserIdAndSocialProvider(Long userId, SocialProvider socialProvider);
}
