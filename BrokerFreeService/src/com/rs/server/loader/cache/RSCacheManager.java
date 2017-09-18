package com.rs.server.loader.cache;

import java.util.HashMap;

public class RSCacheManager {
	static HashMap<String, HashMap<String,Object>> globalCache = new HashMap<String,HashMap<String, Object>>();
	
	public static void setHashMap(String key,HashMap<String,Object> value){
		if(globalCache.get(key) == null){
			globalCache.put(key, value);
			
		}else{
			HashMap keyMap = globalCache.get(key);
			keyMap.putAll(value);
		}
	}
	public static HashMap<String, Object> getHashMapValue(String key){
		HashMap<String, Object> value = globalCache.get(key);
		return value;
	}
}
