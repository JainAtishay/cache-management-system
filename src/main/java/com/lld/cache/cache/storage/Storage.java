package com.lld.cache.cache.storage;

public interface Storage<K, V> {

    void put(K key, V value);
    V getValue(K key);
    void remove(K key);
    boolean isFull();
    void printContent();
}
