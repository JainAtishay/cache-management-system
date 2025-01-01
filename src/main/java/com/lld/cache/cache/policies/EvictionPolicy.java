package com.lld.cache.cache.policies;

public interface EvictionPolicy<Key> {
    void accessedKey(Key key);
    Key keyToRemove();
}
