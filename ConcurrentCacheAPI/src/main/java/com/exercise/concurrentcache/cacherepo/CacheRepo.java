package com.exercise.concurrentcache.cacherepo;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.exercise.concurrentcache.entity.CachedObject;

public class CacheRepo {

	public static final String CACHE_REPO_BEAN = "repo";

	protected static Map<Object, CachedObject> cacheStore = new ConcurrentHashMap<Object, CachedObject>();

	private static Deque<CachedObject> cachedObjectsQueue = new ConcurrentLinkedDeque<CachedObject>();

	private static Map<String, String> configurations = new HashMap<String, String>();

	public static Map<String, String> getConfigurations() {
		return configurations;
	}

	public static void setConfigurations(Map<String, String> configurations) {
		CacheRepo.configurations = configurations;
	}

	// return the cached object based on key
	public static CachedObject get(Object key) {
		return CacheRepo.cacheStore.get(key);

	}

	// Add to concurrent hash map
	public static void putIntoMap(Object key, CachedObject value) {
		cacheStore.put(key, value);
	}

	// remove from concurrent hashMap
	public static void removeFromMap(Object key) {
		cacheStore.remove(key);
	}

	// Add to the Queue for LRU and other cases
	public static void addToQueue(CachedObject value) {
		cachedObjectsQueue.add(value);
	}

	// remove the oldest entry form Queue
	public static CachedObject removeFromQueue() {
		return cachedObjectsQueue.removeFirst();
	}

	// Remove in a bulk from queue
	public static void removeElementsInBulkFromQueue(Set<CachedObject> set) {
		CacheRepo.cachedObjectsQueue.removeAll(set);
	}

	// Remove a specific element from queue
	public static void removeElementfromQueue(CachedObject cachedObject) {
		CacheRepo.cachedObjectsQueue.remove(cachedObject);
	}

	// get the list of the cache objects
	public static Deque<CachedObject> getCachedObjectsQueue() {
		return CacheRepo.cachedObjectsQueue;
	}

	// get the map of the cached objects itself
	public static Map<Object, CachedObject> getCacheStore() {
		return cacheStore;
	}
}
