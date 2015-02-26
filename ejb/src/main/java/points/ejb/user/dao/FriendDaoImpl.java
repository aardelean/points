package points.ejb.user.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import points.ejb.dao.GenericDaoImpl;
import points.user.dao.FriendDao;
import points.user.dto.ContactCollection;
import points.user.dto.SocialProvider;

/**
 * Created by aardelean on 25.10.2014.
 */
@Stateless
public class FriendDaoImpl extends GenericDaoImpl<ContactCollection> implements FriendDao{

    @Override
    public ContactCollection findByUserIdAndSocialProvider(Long userId, SocialProvider socialProvider) {
        Query query = em.createQuery("FROM Friend WHERE friend.user.id=:userId AND friend.socialProvider=:socialProvider", ContactCollection.class);
        query.setParameter("userId", userId);
        query.setParameter("socialProvider", socialProvider);
        ContactCollection contactCollection = null;
        try{
            contactCollection = (ContactCollection)query.getSingleResult();
        }catch(NoResultException e){
        }
        return contactCollection;
    }
}
