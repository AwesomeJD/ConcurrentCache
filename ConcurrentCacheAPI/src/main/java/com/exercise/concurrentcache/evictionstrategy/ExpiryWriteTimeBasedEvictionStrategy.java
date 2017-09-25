package com.exercise.concurrentcache.evictionstrategy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.exercise.concurrentcache.cacheevictions.notifications.CacheEvictionNotifierViaEmail;
import com.exercise.concurrentcache.cacherepo.CacheRepo;
import com.exercise.concurrentcache.constants.CacheConstants;
import com.exercise.concurrentcache.entity.CachedObject;
import com.exercise.concurrentcache.util.CacheUtil;

public class ExpiryWriteTimeBasedEvictionStrategy implements EvictionStrategy {

	private long timeDelay;

	public void processEvictionStrategy() {
		CacheUtil.PrintNewLine("\n\n\nExpiryWriteTimeBasedEvictionStrategy Thread with time delay-- " + this.timeDelay
				+ " STARTED !!");
		CacheUtil.printmap();
		CacheUtil.printQueue();
		Set<CachedObject> tempSet = new HashSet<CachedObject>();

		for (CachedObject cachedObject : CacheRepo.getCachedObjectsQueue()) {
			if (isRemovable(cachedObject)) {
				tempSet.add(cachedObject);
			}
		}
		CacheUtil.PrintNewLine("Elements to remove in ExpiryWriteTimeBasedEvictionStrategy --" + tempSet.size());
		CacheRepo.removeElementsInBulkFromQueue(tempSet);
		for (CachedObject cachedObject : tempSet) {
			CacheRepo.removeFromMap(cachedObject.getKey());
			new CacheEvictionNotifierViaEmail().notifyUsers(cachedObject);
		}
		CacheUtil.PrintNewLine("The ExpiryWriteTimeBasedEvictionStrategy Thread ENDS !! \n");
	}

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

	private boolean isRemovable(CachedObject cachedObject) {
		Long time = System.currentTimeMillis();
		Long timeAtWrite = Long.parseLong(cachedObject.getProperties().get(CacheConstants.TIME_AFTER_WRITE));
		Long timToExpire = Long
				.parseLong(CacheRepo.getConfigurations().get(CacheConstants.TIME_TO_EXPIRE_AFTER_WRITE_IN_SECONDS));
		if (Objects.isNull(time) && Objects.isNull(timeAtWrite) && Objects.isNull(timToExpire)) {
			return false;
		}

		if (time - timeAtWrite > timToExpire * 1000) {
			return true;
		}
		return false;
	}
}
