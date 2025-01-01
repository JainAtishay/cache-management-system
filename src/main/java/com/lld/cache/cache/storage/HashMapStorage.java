package com.lld.cache.cache.storage;

import com.lld.cache.cache.exceptions.KeyNotFoundException;
import com.lld.cache.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapStorage<K, V> implements Storage<K, V> {

    private final Map<K, V> map;
    private final int capacity;

    public HashMapStorage(int capacity) {
        this.map = new ConcurrentHashMap<>();
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if(map.size() == capacity){
            throw new StorageFullException("storage is full");
        }

        map.put(key, value);
    }

    public V getValue(K key) {
       if(!map.containsKey(key)){
           throw new KeyNotFoundException("key not present");
       }

       return map.get(key);
    }

    public void remove(K key) {
        if(!map.containsKey(key)){
            return;
        }
        map.remove(key);
    }

    public boolean isFull() {
        return this.map.size() == capacity;
    }

    public void printContent(){
        System.out.println(map);
    }
}
