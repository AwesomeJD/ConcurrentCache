package com.exercise.concurrentcache.cachepreprocessor;

import com.exercise.concurrentcache.constants.CacheConstants;
import com.exercise.concurrentcache.entity.CachedObject;

public class WriteCachePreprocessor implements WriteCachePreprocessorI {

	public CachedObject preProcess(Object value) {

		CachedObject cachedObject = new CachedObject();
		cachedObject.setValue(value);

		Long currentTime = System.currentTimeMillis();
		cachedObject.setProp(CacheConstants.TIME_AFTER_WRITE, currentTime.toString());
		cachedObject.setProp(CacheConstants.TIME_AFTER_ACCESS, Long.toString(0));
		return cachedObject;
	}

}
