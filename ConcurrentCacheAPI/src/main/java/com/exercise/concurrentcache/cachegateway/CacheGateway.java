package com.exercise.concurrentcache.cachegateway;

import com.exercise.concurrentcache.cachepreprocessor.AccessCachePreprocessorI;
import com.exercise.concurrentcache.cachepreprocessor.PreProcessorRepo;
import com.exercise.concurrentcache.cachepreprocessor.WriteCachePreprocessorI;
import com.exercise.concurrentcache.cacherepo.CacheRepo;
import com.exercise.concurrentcache.entity.CachedObject;

public class CacheGateway {

	public Object getCache(Object key) {
		CachedObject cachedObject = CacheRepo.get(key);
		if (null == cachedObject) {
			return null;
		}
		AccessCachePreprocessorI cachePreprocessor = PreProcessorRepo.getAccessProcessor();
		CachedObject cachedObjectUpdated = cachePreprocessor.preProcess(cachedObject);
		CacheRepo.removeElementfromQueue(cachedObject);
		updateRepo(cachedObjectUpdated);
		return cachedObjectUpdated.getValue();
	}

	public void setCache(Object key, Object value) {
		WriteCachePreprocessorI cachePreprocessor = PreProcessorRepo.getWriteProcessor();
		CachedObject cachedObjectUpdated = cachePreprocessor.preProcess(value);
		cachedObjectUpdated.setKey(key);
		CacheRepo.putIntoMap(key, cachedObjectUpdated);
		CacheRepo.addToQueue(cachedObjectUpdated);
	}

	private void updateRepo(CachedObject cachedObject) {
		CacheRepo.putIntoMap(cachedObject.getKey(), cachedObject);
		CacheRepo.addToQueue(cachedObject);
	}

}
