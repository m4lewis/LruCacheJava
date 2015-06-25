/**
 * Created by mlewis on 25/06/2015.
 */
public interface LruCache {
    Object get(Object key);
    void put(Object key, Object value);
    int getMaxSize();
    String toString();
}
