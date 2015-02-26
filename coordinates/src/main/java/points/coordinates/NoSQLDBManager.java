package points.coordinates;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import points.point.dto.Point;

/**
 * Created by aardelean on 31.01.2015.
 */
//@Singleton
//@Startup
public class NoSQLDBManager {

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

	public Cache getCache(){
		return cache;
	}

}
