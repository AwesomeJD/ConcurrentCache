package com.exercise.concurrentcache.strategyexecuter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.exercise.concurrentcache.evictionstrategy.EvictionStrategy;
import com.exercise.concurrentcache.evictionstrategy.EvictionStrategyRepo;
import com.exercise.concurrentcache.exception.NoEvictionStrategyConfiguredException;
import com.exercise.concurrentcache.util.CacheUtil;

public class EvictionStrategyRunner {

	public static final String EVICTION_STRATEGY_RUNNER = "evictionStrategyRunner";
	private static ExecutorService scheduler;

	public static void start(long delay) throws NoEvictionStrategyConfiguredException {
		List<EvictionStrategy> evictionStrategies = EvictionStrategyRepo.getEvictionStratgies();

		int evictionStrategiesSize = evictionStrategies.size();
		CacheUtil.PrintNewLine("Eviction Strategies configured count is --- " + evictionStrategiesSize);

		if (evictionStrategiesSize == 0) {
			throw new NoEvictionStrategyConfiguredException("No Eviction strategy configured !");
		}
		initializeThreadExecuterService(evictionStrategiesSize);
		for (EvictionStrategy evictionStrategy : evictionStrategies) {
			evictionStrategy.setDelayTime(delay);
			scheduler.submit(evictionStrategy);
		}
	}

	public static void initializeThreadExecuterService(int noOfThreads) {
		scheduler = Executors.newFixedThreadPool(noOfThreads);
	}

	public static void shutDown() {
		scheduler.shutdown();
	}

}
