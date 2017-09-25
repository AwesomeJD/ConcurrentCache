package com.exercise.concurrentcache.util;

import java.util.Iterator;
import java.util.Map;

import com.exercise.concurrentcache.cacherepo.CacheRepo;
import com.exercise.concurrentcache.entity.CachedObject;

public class CacheUtil {

	public static void PrintNewLine(String string) {
		System.out.println(string);
	}

	public static void Print(String string) {
		System.out.print(string);
	}

	public static void printmap() {
		CacheUtil.PrintNewLine("In Map ");
		for (Map.Entry<Object, CachedObject> entry : CacheRepo.getCacheStore().entrySet()) {
			CacheUtil.Print(" " + entry.getKey() + "  " + entry.getValue().getValue() + " ");
		}
		CacheUtil.PrintNewLine("\n");
	}

	public static void printQueue() {
		CacheUtil.PrintNewLine("In Queue ");
		Iterator<CachedObject> iterator = CacheRepo.getCachedObjectsQueue().iterator();
		while (iterator.hasNext()) {
			CachedObject obj = iterator.next();
			CacheUtil.Print(" " + obj.getKey() + " " + obj.getValue() + " ");
		}
		CacheUtil.PrintNewLine("\n");
	}
}
