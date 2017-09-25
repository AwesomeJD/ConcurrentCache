package com.exercise.concurrentcache.evictionstrategy;

public interface EvictionStrategy extends Runnable {

	public void setDelayTime(long time);

	public void processEvictionStrategy();

}
