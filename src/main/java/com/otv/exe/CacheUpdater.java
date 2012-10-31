package com.otv.exe;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.otv.srv.ICacheService;
import com.otv.user.User;

/**
 * @author onlinetechvision.com
 * @since 15 Oct 2011
 * @version 1.0.0
 *
 */
public class CacheUpdater implements Runnable {
	
	private static Logger log = Logger.getLogger(CacheUpdater.class);
	ICacheService cacheService;
	User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ICacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(ICacheService cacheService) {
		this.cacheService = cacheService;
	}

	public void run() {		
		
		//New User are created... 
		//Entries which will be inserted via first member of the cluster so before the project is built 
		// in order to deploy first member of the cluster, this code block should be opened...
		getUser().setName("Bruce");
		getUser().setSurname("Willis");
		
		//Entries are added to cache...
		getCacheService().addToCache("user1", getUser());
		
//		//New User are created... 
//		//Entries which will be inserted via second member of the cluster so before the project is built 
//		// in order to deploy second member of the cluster, this code block should be opened...
//		getUser().setName("Clint");
//		getUser().setSurname("Eastwood");
//		
//		//Entries are added to cache...
//		getCacheService().addToCache("user2", getUser());

		//Cache Entries are being printed...
		printCacheEntries();		
	}
	
	private void printCacheEntries() {
		Collection<User> userCollection = null;
		try {
			while(true) {
				userCollection = (Collection<User>)getCacheService().getCache().values();
				for(User user : userCollection) {
					log.debug("1. Cache Content : "+user);
				}
				Thread.sleep(10000);			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
