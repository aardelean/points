package points;

/**
 * Created by aardelean on 14.12.2014.
 */
public interface GenericFacade<T,E> {
    T convert(E e);
}
