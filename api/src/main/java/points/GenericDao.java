package points;

import javax.ejb.Local;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by aardelean on 04.10.2014.
 */
@Local
public interface GenericDao<T extends Object> {

    @Transactional(Transactional.TxType.REQUIRED)
    T save(T t);

    T findById(Serializable id);

    @Transactional(Transactional.TxType.REQUIRED)
    T update(T t);

    @Transactional(Transactional.TxType.REQUIRED)
    void delete(T t);
}
