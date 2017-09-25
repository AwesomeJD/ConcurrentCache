package com.exercise.concurrentcache.evictionstrategy;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.exercise.concurrentcache.cacheevictions.notifications.CacheEvictionNotifierViaEmail;
import com.exercise.concurrentcache.cacherepo.CacheRepo;
import com.exercise.concurrentcache.constants.CacheConstants;
import com.exercise.concurrentcache.entity.CachedObject;
import com.exercise.concurrentcache.util.CacheUtil;

public class SizeBasedEvictionStrategy implements EvictionStrategy {

	private long timeDelay;

	public void run() {
		for (;;) {
			try {
				TimeUnit.SECONDS.sleep(this.timeDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.processEvictionStrategy();
		}
	}

	public void setDelayTime(long timeDelay) {
		this.timeDelay = timeDelay;
	}

	public void processEvictionStrategy() {
		CacheUtil.PrintNewLine(
				"\n\n\nSizeBasedEvictionStrategy Thread with time delay-- " + this.timeDelay + " STARTED !!");
		CacheUtil.printmap();
		CacheUtil.printQueue();

		String configSizeString = CacheRepo.getConfigurations().get(CacheConstants.SIZE);

		int sizeDiff = Objects.isNull(configSizeString) ? 0
				: CacheRepo.getCachedObjectsQueue().size() - Integer.parseInt(configSizeString);

		CacheUtil
				.PrintNewLine("Elements to remove in SizeBasedEvictionStrategy is--" + ((sizeDiff < 0) ? 0 : sizeDiff));
		while (sizeDiff > 0) {
			// Delete first from Queue
			CachedObject cachedObject = removeLastElementsFromQueue();
			// Delete the same from Map
			removeElementsFromMap(cachedObject);
			// Notify to the users about the cached deleted object
			notifyUsers(cachedObject);
			sizeDiff = sizeDiff - 1;
		}
		CacheUtil.PrintNewLine("The SizeBasedEvictionStrategy Thread ENDS !! \n");
	}

	private CachedObject removeLastElementsFromQueue() {
		return CacheRepo.removeFromQueue();
	}

	private void removeElementsFromMap(CachedObject cachedObject) {
		CacheRepo.removeFromMap(cachedObject.getKey());
	}

	private void notifyUsers(CachedObject cachedObject) {
		new CacheEvictionNotifierViaEmail().notifyUsers(cachedObject);
	}
}
