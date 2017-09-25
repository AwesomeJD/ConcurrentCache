package com.exercise.concurrentcache.manager;

import static org.junit.Assert.assertSame;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exercise.concurrentcache.cachegateway.CacheGateway;
import com.exercise.concurrentcache.cachepreprocessor.AccessCachePreprocessor;
import com.exercise.concurrentcache.cachepreprocessor.WriteCachePreprocessor;
import com.exercise.concurrentcache.configuration.CacheConfiguration;
import com.exercise.concurrentcache.constants.CacheConstants;
import com.exercise.concurrentcache.evictionstrategy.ExpiryWriteTimeBasedEvictionStrategy;
import com.exercise.concurrentcache.evictionstrategy.SizeBasedEvictionStrategy;
import com.exercise.concurrentcache.exception.NoEvictionStrategyConfiguredException;

public class TestConcurrentCacheManager {

	@Test
	public void getBean() throws InterruptedException, NoEvictionStrategyConfiguredException {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				CacheConstants.BEANS_XML);
		ConcurrentCacheManager concurrentCacheManager = (ConcurrentCacheManager) applicationContext
				.getBean(ConcurrentCacheManager.CACHE_MANAGER_BEAN);
		applicationContext.close();

		// prepare configuration
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setConfigurations(CacheConstants.TIME_TO_EXPIRE_AFTER_ACCESS_IN_SECONDS, "2");
		cacheConfiguration.setConfigurations(CacheConstants.TIME_TO_EXPIRE_AFTER_WRITE_IN_SECONDS, "5");
		cacheConfiguration.setConfigurations(CacheConstants.WAIT_TIME_FOR_THE_THREADS, "2");
		cacheConfiguration.setConfigurations(CacheConstants.SIZE, "2");

		// set configuration
		concurrentCacheManager.setConfiguration(cacheConfiguration);

		// set eviction strategy
		concurrentCacheManager.setStrategy(new ExpiryWriteTimeBasedEvictionStrategy());
		concurrentCacheManager.setStrategy(new SizeBasedEvictionStrategy());

		// set preprocessors
		concurrentCacheManager.setAccessCachePreprocessor(new AccessCachePreprocessor());
		concurrentCacheManager.setWriteCachePreprocessor(new WriteCachePreprocessor());

		// set the cache values
		CacheGateway cacheGateway = concurrentCacheManager.getCacheGateway();
		cacheGateway.setCache("JD", "1");
		cacheGateway.setCache("SD", "2");
		cacheGateway.setCache("MS", "3");
		cacheGateway.setCache("JS", "4");
		
		// start the eviction strategy runner
		concurrentCacheManager.start();

		
		assertSame("2", cacheGateway.getCache("SD"));
		assertSame("1", cacheGateway.getCache("JD"));
		assertSame("3", cacheGateway.getCache("MS"));
		assertSame("4", cacheGateway.getCache("JS"));

		//Lets make the application run for some time
		TimeUnit.SECONDS.sleep(60);
		
		// shut down the eviction runner
		concurrentCacheManager.shutDown();
	}
}
