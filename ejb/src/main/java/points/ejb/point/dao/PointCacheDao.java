package points.ejb.point.dao;

//import org.jboss.as.clustering.infinispan.DefaultCacheContainer;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import points.point.dao.PointCacheDaoLocal;
import points.point.dto.Point;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by aardelean on 28.09.2014.
 */
@Singleton
@Startup
public class PointCacheDao implements PointCacheDaoLocal {
//    @Resource(lookup="java:jboss/infinispan/container/point")
//    private DefaultCacheContainer cacheContainer;

    private org.infinispan.Cache<Long, Point> cache;

    private EmbeddedCacheManager manager;

    @PostConstruct
    public void initCache()  {
        if(manager == null) {
            new GlobalConfigurationBuilder().globalJmxStatistics().allowDuplicateDomains(true).jmxDomain("point-domain");
            Configuration configuration = new ConfigurationBuilder().clustering().cacheMode(CacheMode.LOCAL)
                    .jmxStatistics().disable().build();
            manager = new DefaultCacheManager(configuration);
            cache = manager.getCache();
        }

    }
    @PreDestroy
    public void tearUp(){
        if(manager!=null) {
            manager.stop();
        }
    }

    @Override
    public void store(Point point, Long userId) {
        cache.put(userId,point);
    }

    @Override
    public Point get(Long userId) {
        return cache.get(userId);
    }

}
