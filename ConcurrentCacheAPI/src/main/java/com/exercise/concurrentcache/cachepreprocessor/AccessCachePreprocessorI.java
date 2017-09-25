package com.exercise.concurrentcache.cachepreprocessor;

import com.exercise.concurrentcache.entity.CachedObject;

public interface AccessCachePreprocessorI {
	public CachedObject preProcess(CachedObject cachedObject);
}
