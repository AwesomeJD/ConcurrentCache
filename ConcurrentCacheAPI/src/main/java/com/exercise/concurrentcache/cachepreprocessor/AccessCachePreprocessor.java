package com.exercise.concurrentcache.cachepreprocessor;

import com.exercise.concurrentcache.constants.CacheConstants;
import com.exercise.concurrentcache.entity.CachedObject;

public class AccessCachePreprocessor implements AccessCachePreprocessorI {

	public CachedObject preProcess(CachedObject cachedObject) {
		Long currentTime = System.currentTimeMillis();
		cachedObject.setProp(CacheConstants.TIME_AFTER_ACCESS, currentTime.toString());
		return cachedObject;
	}

}
