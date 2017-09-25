package com.exercise.concurrentcache.evictionstrategy;

import java.util.ArrayList;
import java.util.List;

public class EvictionStrategyRepo {

	public static final String EVIC_STRATEGY_REPO_BEAN = "evicStrategyRepo";

	private static List<EvictionStrategy> evictionStratgies = new ArrayList<EvictionStrategy>();

	// get the eviction strategies
	public static List<EvictionStrategy> getEvictionStratgies() {
		return evictionStratgies;
	}

	public static void setEvictionStratgies(EvictionStrategy evictionStrategy) {
		EvictionStrategyRepo.evictionStratgies.add(evictionStrategy);
	}

}
