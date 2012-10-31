package com.otv.srv;

import com.tangosol.net.NamedCache;

/**
 * @author onlinetechvision.com
 * @since 15 Oct 2011
 * @version 1.0.0
 *
 */
public interface ICacheService {

	public NamedCache getCache();
	public void addToCache(Object key, Object value);
	public void deleteFromCache(Object key);
	
}
