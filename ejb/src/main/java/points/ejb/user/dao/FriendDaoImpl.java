package points.ejb.user.dao;

import points.ejb.dao.GenericDaoImpl;
import points.user.dao.FriendDao;
import points.user.dto.ContactCollection;
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
public class FriendDaoImpl extends GenericDaoImpl<ContactCollection> implements FriendDao{

    @PersistenceContext
    private EntityManager em;

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
