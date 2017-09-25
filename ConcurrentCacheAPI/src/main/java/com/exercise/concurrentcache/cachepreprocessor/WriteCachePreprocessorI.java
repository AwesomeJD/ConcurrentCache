package com.exercise.concurrentcache.cachepreprocessor;

import com.exercise.concurrentcache.entity.CachedObject;

public interface WriteCachePreprocessorI {
	public CachedObject preProcess(Object value);
}
