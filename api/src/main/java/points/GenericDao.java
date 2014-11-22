package points;

import javax.ejb.Local;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by aardelean on 04.10.2014.
 */
@Local
public interface GenericDao<T extends Object> {

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void save(T t);

    T findById(Serializable id);

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void update(T t);

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void delete(T t);
}
