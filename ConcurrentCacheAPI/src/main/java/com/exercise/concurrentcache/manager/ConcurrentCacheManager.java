package com.exercise.concurrentcache.manager;

import com.exercise.concurrentcache.cachegateway.CacheGateway;
import com.exercise.concurrentcache.cachepreprocessor.AccessCachePreprocessor;
import com.exercise.concurrentcache.cachepreprocessor.PreProcessorRepo;
import com.exercise.concurrentcache.cachepreprocessor.WriteCachePreprocessor;
import com.exercise.concurrentcache.cacherepo.CacheRepo;
import com.exercise.concurrentcache.configuration.CacheConfiguration;
import com.exercise.concurrentcache.constants.CacheConstants;
import com.exercise.concurrentcache.evictionstrategy.EvictionStrategy;
import com.exercise.concurrentcache.evictionstrategy.EvictionStrategyRepo;
import com.exercise.concurrentcache.exception.NoEvictionStrategyConfiguredException;
import com.exercise.concurrentcache.strategyexecuter.EvictionStrategyRunner;

public class ConcurrentCacheManager {

	public static final String CACHE_MANAGER_BEAN = "manager";

	public void setConfiguration(CacheConfiguration cacheConfiguration) {
		CacheRepo.setConfigurations(cacheConfiguration.getProperties());
	}

	public void setStrategy(EvictionStrategy evictionStrategy) {
		EvictionStrategyRepo.setEvictionStratgies(evictionStrategy);
	}

	public CacheGateway getCacheGateway() {
		return new CacheGateway();
	}

	public void setWriteCachePreprocessor(WriteCachePreprocessor writeCachePreprocessor) {
		PreProcessorRepo.setWriteProcessor(writeCachePreprocessor);
	}

	public void setAccessCachePreprocessor(AccessCachePreprocessor accessCachePreprocessor) {
		PreProcessorRepo.setAccessProcessor(accessCachePreprocessor);
	}

	public void start() throws NoEvictionStrategyConfiguredException {
		Long delay = Long.parseLong(CacheRepo.getConfigurations().get(CacheConstants.WAIT_TIME_FOR_THE_THREADS));
		EvictionStrategyRunner.start(delay);
	}

	public void shutDown() {
		EvictionStrategyRunner.shutDown();
	}
}
