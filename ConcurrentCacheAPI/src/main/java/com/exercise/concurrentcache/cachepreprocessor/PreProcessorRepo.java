package com.exercise.concurrentcache.cachepreprocessor;

public class PreProcessorRepo {

	public static final String PREPROCESSOR_REPO_BEAN = "preprocessorRepo";

	private static AccessCachePreprocessorI accessProcessor;
	private static WriteCachePreprocessorI writeProcessor;

	public static AccessCachePreprocessorI getAccessProcessor() {
		return accessProcessor;
	}

	public static void setAccessProcessor(AccessCachePreprocessorI accessProcessor) {
		PreProcessorRepo.accessProcessor = accessProcessor;
	}

	public static WriteCachePreprocessorI getWriteProcessor() {
		return writeProcessor;
	}

	public static void setWriteProcessor(WriteCachePreprocessorI writeProcessor) {
		PreProcessorRepo.writeProcessor = writeProcessor;
	}

}
