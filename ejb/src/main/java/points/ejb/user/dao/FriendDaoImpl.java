package points.ejb.user.dao;

import points.ejb.dao.GenericDaoImpl;
import points.user.dao.FriendDao;
import points.user.dto.Friend;
import points.user.dto.SocialProvider;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by aardelean on 25.10.2014.
 */
@Stateless
public class FriendDaoImpl extends GenericDaoImpl<Friend> implements FriendDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Friend findByUserIdAndSocialProvider(Long userId, SocialProvider socialProvider) {
        Query query = em.createQuery("FROM Friend WHERE friend.user.id=:userId AND friend.socialProvider=:socialProvider", Friend.class);
        query.setParameter("userId", userId);
        query.setParameter("socialProvider", socialProvider);
        Friend friend = null;
        try{
            friend = (Friend)query.getSingleResult();
        }catch(NoResultException e){
        }
        return friend;
    }
}
