package com.exercise.concurrentcache.configuration;

import java.util.HashMap;
import java.util.Map;

public class CacheConfiguration {

	private Map<String, String> configurations;

	public CacheConfiguration() {
		this.configurations = new HashMap<String, String>();
	}

	public void setConfigurations(String key, String value) {
		this.configurations.put(key, value);
	}

	public Map<String, String> getProperties() {
		return this.configurations;
	}
}
