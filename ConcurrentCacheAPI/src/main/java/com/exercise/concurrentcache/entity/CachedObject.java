package com.exercise.concurrentcache.entity;

import java.util.HashMap;
import java.util.Map;

public class CachedObject {

	private Object key;

	private Object value;

	private Map<String, String> properties = new HashMap<String, String>();

	public void setProp(String key, String value) {
		this.properties.put(key, value);
	}

	public Map<String, String> getProperties() {
		return this.properties;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}
}
