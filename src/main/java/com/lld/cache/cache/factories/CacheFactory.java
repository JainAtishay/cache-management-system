package com.lld.cache.cache.factories;

import com.lld.cache.cache.Cache;
import com.lld.cache.cache.policies.LruEvictionPolicy;
import com.lld.cache.cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCacheFactory(int capacity){
        return new Cache<Key, Value>(new HashMapStorage<Key, Value>(capacity), new LruEvictionPolicy<Key>());
    }
}
