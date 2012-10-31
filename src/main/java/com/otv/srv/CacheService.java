package com.otv.srv;

import org.apache.log4j.Logger;

import com.otv.listener.UserMapListener;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * @author onlinetechvision.com
 * @since 15 Oct 2011
 * @version 1.0.0
 *
 */
public class CacheService implements ICacheService {
	
	private static Logger log = Logger.getLogger(CacheService.class);
	private NamedCache cache = null;	
	private static final String USER_MAP = "user-map";
	private static final long LOCK_TIMEOUT = -1;
	
	public CacheService() {
		cache = CacheFactory.getCache(USER_MAP);
		cache.addMapListener(new UserMapListener());		
	}	
	
	public NamedCache getCache() {
		return cache;
	}

	public void setCache(NamedCache cache) {
		this.cache = cache;
	}

	public void addToCache(Object key, Object value) {
		// key is locked
		cache.lock(key, LOCK_TIMEOUT);
		try {
			// application logic
			cache.put(key, value);
		} finally {
			// key is unlocked
			cache.unlock(key);
		}
	}

	public void deleteFromCache(Object key) {
		// key is locked
		cache.lock(key, LOCK_TIMEOUT);
		try {
			// application logic
			cache.remove(key);
		} finally {
			// key is unlocked
			cache.unlock(key);
		}
	}		
}
