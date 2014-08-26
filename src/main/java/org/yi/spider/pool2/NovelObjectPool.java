package org.yi.spider.pool2;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.yi.spider.service.INovelService;

public class NovelObjectPool {

	private static GenericKeyedObjectPool<String, INovelService> novelPool;
	
	private NovelObjectPool() {
		
	}
	
	public static GenericKeyedObjectPool<String, INovelService> getNovelPool() {
		if(novelPool == null) {
			synchronized (NovelObjectPool.class) {  
				if(novelPool == null) { 
					novelPool = new GenericKeyedObjectPool<String, INovelService>(new NovelPooledObjectFactory());
				}
			}
		}
		return novelPool;
	}
	

}
